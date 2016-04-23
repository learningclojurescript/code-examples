(ns experiment.async
  "Logic for handling messages from the kitten factory."
  (:require [cljs.core.async :as async]
            [ajax.core :refer [GET]])
  (:require-macros
    [cljs.core.async.macros :as async-macros]))

(defn error?
  [x]
  (instance? js/Error x))

(defn throw-err
  [x]
  (if (error? x)
    (throw x)
    x))

(def channel2 (async/chan 5 (map throw-err)))

(defn enqueue-val
  "Enqueue a new value into our channel."
  [c v]
  (async-macros/go
    (async/>! c v)))

(defn kitten-factory
  []
  (GET "/kitten-factory"
       {:handler (fn [res] (enqueue-val channel res))
        :error-handler (fn [err] (enqueue-val channel (js/Error. err)))}))

(defn listen
  []
  (async-macros/go
    (while true
      (js/console.log (async/<! channel))))
  )

#_(defn listen
  []
  "Listen for the latest message from the kitten factory channel."
  (async-macros/go
    (while true
      (try
        (let [v (async/<! channel)]
          ;; (send-success-report-to-cat-hq)
          (js/console.log "All good cap")
          )
          (catch js/Error e
            (js/console.log "An error happened!")
            ;; (send-error-report-to-cat-hq e)
            )))))

