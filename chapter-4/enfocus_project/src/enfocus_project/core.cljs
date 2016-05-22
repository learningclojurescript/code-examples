(ns enfocus-project.core
  (:require [enfocus.core :as ef]
            [enfocus.events :as events]
            [enfocus.effects :as effects])
  (:require-macros [enfocus.macros :as em]))

(defn gen-button
  [id caption]
  (ef/html [(keyword  (str "button#" id)) caption]))

(defn say-hello!
  []
  (ef/at js/document
         ["#a-div"] (ef/content "Hello From Enfocus!")
         ["body"] (ef/append (gen-button "btn1" "Click me!"))
         ["body"] (ef/append (gen-button "btn2" "Resize the div!"))))

(em/defaction activate-button! []
  ["#btn1"] (events/listen :click #(js/alert "I am Clicked!")))

(em/defaction resize-div! [param]
  ["#a-div"] (effects/chain
                 (effects/resize param :curheight 500 )
                 (effects/resize :curwidth (* 2 param) 500)))

(em/defaction activate-resize! []
  ["#btn2"] (events/listen :click #(resize-div! 200)))





