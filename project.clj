(defproject clj-bidi-liberator-swagger-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :plugins [[lein-ring "0.12.5"]]

  ;:ring {:handler clj-bidi-liberator-swagger-example.core/compojure-handler}
  :ring {:handler clj-bidi-liberator-swagger-example.core/bidi-handler}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [liberator "0.15.3"]
                 [bidi "2.1.6"]
                 [compojure "1.6.0"]
                 [ring/ring-core "1.7.1"]]
  :repl-options {:init-ns clj-bidi-liberator-swagger-example.core})
