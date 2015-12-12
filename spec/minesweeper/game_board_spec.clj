(ns minesweeper.game-board-spec
	(:require [speclj.core :refer :all])
	(:require [minesweeper.game-board :refer :all]))

(def ^{:private true} board [[0 0]
							[0 -1]])

(describe "open-cell"
	(it "should give mine-signal(-1) on openning mine cell"
    	(should= -1 (open-cell board :row 1 :col 1))))

(run-specs)