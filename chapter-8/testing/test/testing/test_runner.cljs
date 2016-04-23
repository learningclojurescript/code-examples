(ns testing.test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [cljs.test :refer-macros [use-fixtures]]
            [testing.app-test]
            [testing.async-test]
            [testing.core-test]))

;; This isn't strictly necessary, but is a good idea depending
;; upon your application's ultimate runtime engine.
(enable-console-print!)

(doo-tests 'testing.app-test
           'testing.async-test
           'testing.core-test)
