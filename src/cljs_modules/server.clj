(ns cljs-modules.server
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :as resource]))

(defn handler [req]
  {:status 200
   :body (slurp "resources/public/index.html")})

(def handler (-> handler
                 (resource/wrap-resource "public")))

(defn main []
  (jetty/run-jetty handler {:port 8080
                            :host "0.0.0.0"
                            :join? false}))
