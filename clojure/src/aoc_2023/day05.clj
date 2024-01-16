(ns aoc-2023.day05)

(def island-almanac {:seeds [79 14 55 13],
                     :seed-to-soil [[50 98 2],
                                    [52 50 48]],
                     :soil-to-fertilizer [[0 15 37],
                                          [37 52 2],
                                          [39 0 15]],
                     :fertilizer-to-water [[49 53 8],
                                           [0 11 42],
                                           [42 0 7],
                                           [57 7 4]],
                     :water-to-light [[88 18 7],
                                      [18 25 70]],
                     :light-to-temparature [[45 77 23],
                                            [81 45 19],
                                            [68 64 13]],
                     :temperature-to-humidity [[0 69 1],
                                               [1 0 69]],
                     :humidity-to-location [[60 56 37],
                                            [56 93 4]]})


; The rest of the almanac contains a list of maps which describe how to convert 
; numbers from a source category into numbers in a destination category.

; Rather than list every source number and its corresponding destination number one by one, 
; the maps describe entire ranges of numbers that can be converted. 
; Each line within a map contains three numbers: the destination range start, the source range start, and the range length.

; Using these maps, find the lowest location number that corresponds to any of the initial seeds.
; To do this, you'll need to convert each seed number through other categories until you can find its corresponding location number.

(defn calculate-range [number length]
  (let [end (+ number length)]
    (range number end)))

(defn generate-range-map [[destination source length]]
  (let [destination-range (calculate-range destination length)
        source-range      (calculate-range source length)]
    {:destination-range destination-range,
     :source-range      source-range}))

(defn seed-to-soil-map [seed seed-to-soil]
  (println seed)
  (println seed-to-soil)
  (let [soil-range-map (map generate-range-map seed-to-soil)
        source-range (:source-range (nth soil-range-map 1))]
    (println soil-range-map)
    (println source-range)
    (println (some #{seed} source-range))))

(defn find-lowest-location [almanac]
  (let [seeds (:seeds almanac)
        seed-to-soil (seed-to-soil-map (nth seeds 0) (:seed-to-soil almanac))]
    ;(println seed-to-soil)
    ;(println (map generate-range-map seed-to-soil))
    ))

(find-lowest-location island-almanac)

(println (calculate-range 50 48))

; seeds é uma lista
; Cada seed corresponde a um range do seed-to-soil
; seed-to-soil pode ter n valores.
; Em todos os mapas, source e destination são atrelados. Cada source input gera um destination output.
; Se não gerar um destination output, o valor de destination é o mesmo do input.
; O destination output é utilizado como input para a próxima execução.

; Mapear para cada semente o solo, e para o solo gerado, mapear o fertilizando, etc.
; Não é mapear a semente para cada solo! Nem mapear o solo para cada fertilizante! 