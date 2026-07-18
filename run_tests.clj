(require '[clojure.test :as t])
(def suites
  '[tsugite.methods.test-datom-emit
    tsugite.methods.test-ie-flow
    tsugite.tests.test-analyze
    tsugite.tests.test-coverage
    tsugite.tests.test-double-jeopardy
    tsugite.tests.test-kotoba
    tsugite.murakumo-test
    tsugite.repository-contract-test])
(apply require suites)
(let [{:keys [fail error] :as result} (apply t/run-tests suites)]
  (println (select-keys result [:test :pass :fail :error]))
  (when (pos? (+ fail error)) (System/exit 1)))
