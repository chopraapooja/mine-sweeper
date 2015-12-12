(ns minesweeper.game-board-spec
	(:require [speclj.core :refer :all])
	(:require [minesweeper.game-board :refer :all]))

(def ^{:private true} board [[0 0]
							[0 -1]])

(describe "open-cell"
	(it "should give mine-signal(-1) on opening mine cell"
    	(should= -1 (open-cell board :row 1 :col 1)))
	
	(it "should not give mine-signal(-1) on opening not mine cell"
    	(should-not= -1 (open-cell board :row 0 :col 0))
    	(should-not= -1 (open-cell board :row 0 :col 1))
    	(should-not= -1 (open-cell board :row 1 :col 0))))

(run-specs)