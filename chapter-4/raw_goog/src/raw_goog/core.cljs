(ns raw-goog.core
  (:require [goog.dom :as dom]
            [goog.events :as events]))

(def  cnt-holder (dom/getElement "clicks"))
(def  reset-btn (dom/getElement "reset-btn"))
(def  cnt (atom 0))

(defn inc-clicks!
  []
  (dom/setTextContent cnt-holder (swap! cnt inc)))

(defn reset-clicks!
  []
  (dom/setTextContent cnt-holder (reset! cnt 0)))

(events/listen cnt-holder "click" inc-clicks!)
(events/listen reset-btn "click" reset-clicks!)



