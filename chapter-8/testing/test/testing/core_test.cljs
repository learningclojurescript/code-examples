(ns testing.core-test
  (:require [cljs.test :refer-macros [deftest is testing]]))

(deftest i-should-fail
  (is (= 1 0)))

(deftest i-should-succeed
  (is (= 1 1)))
