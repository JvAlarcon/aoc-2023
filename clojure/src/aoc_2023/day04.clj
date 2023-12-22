(ns aoc-2023.day04
  (:use [clojure pprint])
  (:require [clojure.set :as c.set]))

(def scratchcards [{:id 1,
                    :winning-numbers [41, 48, 83, 86, 17],
                    :bet-numbers [83, 86,  6, 31, 17,  9, 48, 53]},
                   {:id 2,
                    :winning-numbers [13, 32, 20, 16, 61],
                    :bet-numbers [61, 30, 68, 82, 17, 32, 24, 19]},
                   {:id 3,
                    :winning-numbers [1, 21, 53, 59, 44],
                    :bet-numbers [69, 82, 63, 72, 16, 21, 14, 1]},
                   {:id 4,
                    :winning-numbers [41, 92, 73, 84, 69],
                    :bet-numbers [59, 84, 76, 51, 58, 5, 54, 83]},
                   {:id 5,
                    :winning-numbers [87, 83, 26, 28, 32],
                    :bet-numbers [88, 30, 70, 12, 93, 22, 82, 36]},
                   {:id 6,
                    :winning-numbers [31, 18, 13, 56, 72],
                    :bet-numbers [74, 77, 10, 23, 35, 67, 36, 11]}])

; card 1 has five winning numbers (41, 48, 83, 86, and 17) and
; eight numbers you have (83, 86, 6, 31, 17, 9, 48, and 53).
; Of the numbers you have, four of them (48, 83, 17, and 86) are winning numbers!
; That means card 1 is worth 8 points (1 for the first match, then doubled three times for each of
; the three matches after the first).
; So, in this example, the Elf's pile of scratchcards is worth 13 points.
; Take a seat in the large pile of colorful cards. How many points are they worth in total?

(defn find-bet-winning-numbers [{card-id :id
                                 winning-numbers :winning-numbers
                                 bet-numbers :bet-numbers}]
  (let [set-winning (into #{} winning-numbers)
        set-bet (into #{} bet-numbers)
        bet-winning-numbers (c.set/intersection set-bet set-winning)]
    (if (not-empty bet-winning-numbers)
      {:id card-id
       :bet-winning-numbers bet-winning-numbers})))

(defn get-points [points]
  (loop [counter points
         acc 1]
    (if (= counter 1)
      acc
      (recur (dec counter) (* acc 2)))))

(defn map-points [{bet-winning-numbers :bet-winning-numbers}]
  (get-points (count bet-winning-numbers)))

(defn find-scratchcards-total-points [cards]
  (->> cards
       (map find-bet-winning-numbers)
       (remove nil?)
       (map map-points)
       (reduce +)
       (println "Scratchcards points in total:")
       ))

(find-scratchcards-total-points scratchcards)