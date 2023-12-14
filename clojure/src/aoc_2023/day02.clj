(ns aoc-2023.day02
  (:use [clojure pprint]))

(def cubes-game [{:game 1,
                  :sets [{:red 4, :blue 3},
                         {:red 1, :green 2, :blue 6},
                         {:green 2}]},
                 {:game 2,
                  :sets [{:green 2, :blue 1},
                         {:red 1, :green 3, :blue 4},
                         {:green 1, :blue 1}]},
                 {:game 3,
                  :sets [{:red 20, :green 8, :blue 6},
                         {:red 4, :green 13, :blue 5},
                         {:red 1, :green 5}]},
                 {:game 4,
                  :sets [{:red 3, :green 1, :blue 6},
                         {:red 6, :green 3},
                         {:red 14, :green 3, :blue 15}]},
                 {:game 5,
                  :sets [{:red 6, :green 3, :blue 1},
                         {:red 1, :green 2, :blue 2}]}])

(def red-cubes-limit 12)
(def green-cubes-limit 13)
(def blue-cubes-limit 14)

; The Elf would first like to know which games would have been possible
; if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?
; Determine which games would have been possible if the bag had been
; loaded with only 12 red cubes, 13 green cubes, and 14 blue cubes.
; What is the sum of the IDs of those games?

(defn sum-cubes [color sets]
  (->> sets
       (map color)
       (remove nil?)
       (reduce +)
       ))

(defn is-valid? [cube-color limit]
  (<= cube-color limit))
(defn set-is-valid? [red green blue]
  (let [is-red-valid? (is-valid? red red-cubes-limit)
        is-green-valid? (is-valid? green green-cubes-limit)
        is-blue-valid? (is-valid? blue blue-cubes-limit)]
    (and is-red-valid?
         is-green-valid?
         is-blue-valid?)))
(defn find-valid-sets [{id :game
                        sets :sets}]
  (let [red (sum-cubes :red sets)
        green (sum-cubes :green sets)
        blue (sum-cubes :blue sets)]
    (if (set-is-valid? red green blue)
      {:id id
       :sets sets})))

(defn sum-ids [valid-games]
  (println "Valid games:")
  (pprint valid-games)
  (->> valid-games
       (map :id)
       (reduce +)))

(defn find-valid-games [games]
  (->> games
       (map find-valid-sets)
       (remove nil?)
       (sum-ids)
       (println "Sum of IDs from valid games:")))

(find-valid-games cubes-game)