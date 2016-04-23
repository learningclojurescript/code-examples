(ns testing.app-test
  (:require [cljs.test :refer-macros [deftest is use-fixtures]]))

;; Run these fixtures for each test
(use-fixtures
  :each
  {:before (fn [] (println "Setting up tests..."))
   :after (fn [] (println "Tearing down tests..."))})

(deftest another-successful-test
  ;; Give us an idea of when this test actually executes.
  (println "Running a test...")
  (is (= 4 (count "test"))))
