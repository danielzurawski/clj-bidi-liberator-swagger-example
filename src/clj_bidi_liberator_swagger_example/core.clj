(ns clj-bidi-liberator-swagger-example.core
  (:require
    [liberator.core :refer [resource defresource]]
    [ring.middleware.params :refer [wrap-params]]
    [compojure.core :refer [defroutes ANY]]
    [bidi.ring :refer [make-handler]]))

;; Domain logic
(defn print-text [text]
  (format "The text is %s" text))


;; Liberator resources
; Compojure
(defresource for-compojure-resource [txt]
  :available-media-types ["text/plain"]
  :exists? (fn [_] txt)
  :handle-ok (fn [_] (print-text txt)))

; Bidi
(defresource for-bidi-resource [print-text-fn]
  :available-media-types ["text/plain"]
  :exists? (fn [ctx] {::text (get-in ctx [:request :route-params :text])})
  :handle-ok (fn [ctx] (print-text-fn (::text ctx))))


;; Routes
; Compojure
(defroutes compojure-routes
  (ANY "/foo/:txt" [txt] (for-compojure-resource txt)))

; Bidi
(def bidi-routes ["/foo/" {[:text] (for-bidi-resource print-text)}])


;; Ring Handler
; Compojure
(def compojure-handler
  (-> compojure-routes
    wrap-params))

; Bidi
(def bidi-handler
  (->
    (make-handler bidi-routes)
    wrap-params))
