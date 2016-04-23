(ns experiment.macros
  (:require [clojure.zip :as zip]))

(defn replace-v
  "Given a zipper, replace all instances of oldv with newv"
  [d oldv newv]
  (loop [loc d]
    (if (zip/end? loc)
      (zip/root loc)
      (recur
       (zip/next
        (if (= (zip/node loc) oldv)
          (zip/replace loc newv)
          loc))))))

(defmacro replace-anything
  [body oldv newv]
  (replace-v (clojure.zip/seq-zip body) oldv newv))

(defmacro <?
  "Actively throw an exception if something goes wrong when waiting on a channel message."
  [expr]
  `(experiment.helpers/throw-err (cljs.core.async/<! ~expr)))

(defmacro increment
  [x]
  `(+ 1 ~x))

(defmacro printer
  [x]
  (println x))

(defmacro fnswap
  [f body]
  `(~f ~@(rest body)))

(defmacro good-binding
  []
  `(let [x# 5]
     x#))

(defmacro db-metrics
  [body]
  `(do
     (analyze ~body)
     (log ~body)))
