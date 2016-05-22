(ns raw-dom.core)

(def  cnt-holder (.getElementById js/document "clicks"))
(def  reset-btn (.getElementById js/document "reset-btn"))
(def  cnt (atom 0))

(defn inc-clicks!
  []
  (set! (.-innerHTML cnt-holder) (swap! cnt inc)))


(defn reset-clicks!
  []
  (set! (.-innerHTML cnt-holder) (reset! cnt -1)))

(set! (.-onclick js/document) inc-clicks!)
(set! (.-onclick reset-btn) reset-clicks!)


