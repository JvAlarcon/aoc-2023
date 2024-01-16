(ns aoc-2023.day06)

(def races [{:time 7,
             :distance 9},
            {:time 15,
             :distance 40},
            {:time 30,
             :distance 200}])

; The first race lasts 7 milliseconds. The record distance in this race is 9 millimeters.
; The second race lasts 15 milliseconds. The record distance in this race is 40 millimeters.
; The third race lasts 30 milliseconds. The record distance in this race is 200 millimeters

; Your toy boat has a starting speed of zero millimeters per millisecond. 
; For each whole millisecond you spend at the beginning of the race holding down the button, 
; the boat's speed increases by one millimeter per millisecond.

; Determine the number of ways you could beat the record in each race. What do you get if you multiply these numbers together?
(defn generate-time-range [total-time]
  (->> (range total-time)
       (remove #{0, total-time})))

(defn distance-by-time
  ([record-time]
   (fn [time-holding] (distance-by-time record-time time-holding)))
  ([record-time time-holding]
   (let [time-left (- record-time time-holding)] 
     (* time-holding time-left))))

(defn winning-distance? 
  ([record-distance]
   (fn [distance] (winning-distance? record-distance distance)))
  ([record-distance distance] 
   (if (> distance record-distance) 
     distance)))

(defn find-winning-possibilities [{record-time :time
                                   record-distance :distance}]
  (let [time-range (generate-time-range record-time)
        distances (map (distance-by-time record-time) time-range)]
    (->> distances
         (map (winning-distance? record-distance))
         (remove nil?))))

(defn count-possibilities [winning-possibilities]
  (let [counter (count winning-possibilities)]
    (println "Winning possibilites in this race:" counter)
    counter))

(defn win-races [races]
  (->> races
       (map find-winning-possibilities)
       (map count-possibilities)
       (reduce *)
       (println "Margin error is:")))

(win-races races)