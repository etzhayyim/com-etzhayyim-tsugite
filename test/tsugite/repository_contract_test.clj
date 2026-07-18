(ns tsugite.repository-contract-test
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(deftest repository-boundary
  (let [c (edn/read-string (slurp "repository-contracts.edn"))]
    (is (= :edn (get-in c [:canonical :format])))
    (doseq [p ["manifest.edn" "schema.edn" "data/seed-peoples-graph.kotoba.edn"
               "wire/manifest.jsonld"]]
      (is (.isFile (io/file p)) p))
    (doseq [p (:forbidden-root-paths c)]
      (is (not (.exists (io/file p))) p))))

(deftest exact-flat-project
  (let [d (edn/read-string (slurp "dependencies.edn"))]
    (is (= "orgs/etzhayyim/com-etzhayyim-tsugite" (:west/project d)))
    (is (re-matches #"[0-9a-f]{40}" (get-in d [:root :revision])))))
