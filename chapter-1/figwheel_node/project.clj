(defproject figwheel-node "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]]
  :plugins [[lein-cljsbuild "1.1.0"]
                [lein-figwheel "0.4.0"]]
  :clean-targets ^{:protect false} ["out"]
  :cljsbuild {
    :builds [{:id "server-dev"
                  :source-paths ["src"]
                  :figwheel true
                  :compiler {:main figwheel-node-repl.core
                                   :output-to "out/figwheel_node_repl.js"
                                   :output-dir "out"
                                   :target :nodejs
                                   :optimizations :none
                                   :source-map true}}]}
  :figwheel {})
