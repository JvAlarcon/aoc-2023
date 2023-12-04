(ns aoc-2023.day01
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str]))

;The newly-improved calibration document consists of lines of text;
; each line originally contained a specific calibration value
; that the Elves now need to recover.
; On each line, the calibration value can be found by combining the
; first digit and the last digit (in that order) to form a single
; two-digit number.

(def input (.getPath (io/resource "day01-input.txt")))

(defn parse-int [s]
  (Integer/parseInt s))

(defn combine-first-&-last-digit [word]
  (if (> (count word) 2)
    (str (first word) (last word))
    (if (= (count word) 1)
      (str word word)
      word)))

(defn form-two-digits [word]
  (->>
    (re-seq #"[0-9]+" word)
    (apply str)
    (combine-first-&-last-digit)))

(defn read-input [input]
  (str/split-lines (slurp input)))

(defn calibrate []
  (let [input (.getPath (io/resource "day01-input.txt"))
        words (read-input input)
        two-digits-list (map form-two-digits words)]
    (->> two-digits-list
         (map parse-int)
         (reduce +)
         (println))))
(calibrate)