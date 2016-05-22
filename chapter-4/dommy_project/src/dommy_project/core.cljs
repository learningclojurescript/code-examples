(ns dommy-project.core
  (:require 
   [dommy.core :as dommy :refer-macros [sel sel1]]))

(defn set-borders!
  []
  (let [ all-ps (sel [:#a-div :p])]
    (->> all-ps
         (map  #(dommy/remove-class! % :changeme))
         (map  #(dommy/add-class! % :border))
         (map  #(dommy/set-text! % "I now have a border!")))))

(defn add-btn!
  []
  (let [the-div (sel1 :#a-div)
        a-btn (dommy/create-element "button")]
    (dommy/set-text! a-btn "Click me!")
    (dommy/listen! a-btn :click
                   (fn[e] (js/alert "You clicked me!")))
    (-> the-div
        (dommy/append! a-btn))))

