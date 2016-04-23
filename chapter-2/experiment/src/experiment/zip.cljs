(ns experiment.zip
  (:require-macros [experiment.macros :as macros])
  (:require [clojure.zip :as zip]))

(defn replace-val
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

(defn termp
  [d oldv newv]
  (println d))

#_(defn replace-values
  [body oldv newv]
  (macros/replace-anything body oldv newv))

(defn to-json
  "Given a piece of data, return a JSON-encoded string."
  [d]
  (with-out-str
    (cond
    (map? d) (str "{" (to-json (zip/seq-zip (seq d))) "}"))
    (and (vector? d)
         (not= d (zip/vector-zip d))) (str "[" (to-json (zip/vector-zip d)) "]")
    (not (vector? d)) (str d)
    :else (loop [loc d]
            (if (zip/end? loc)
              (zip/root loc)
              (recur
                (zip/next
                  loc))))))

