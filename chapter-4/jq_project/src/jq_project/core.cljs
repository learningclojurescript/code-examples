(ns jq-project.core
  (:use [jayq.core :only [$ css html]]))

(def $some-div ($ :#some-div))

(defn change-the-div
  []
  (-> $some-div
      (css {:background "cyan"})
      (html "changed Inner HTML")))

