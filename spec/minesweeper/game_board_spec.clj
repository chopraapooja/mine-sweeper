(ns minesweeper.game-board-spec
	(:require [speclj.core :refer :all])
	(:require [minesweeper.game-board :refer :all]))

(def ^{:private true} board [[-1 0 0]		; [-1 1 0]
							 [0  0 0]		; [2  2 1]
							 [0 -1 0]])		; [1 -1 1]

(describe "open-cell"
	(it "should give bomb-signal(-1) on opening bomb cell"
    	(should= -1 (open-cell board :row 0 :col 0)))
	
	(it "should not give bomb-signal(-1) on opening not bomb cell"
    	(should-not= -1 (open-cell board :row 0 :col 1))
    	(should-not= -1 (open-cell board :row 0 :col 2))
    	(should-not= -1 (open-cell board :row 1 :col 0))
    	(should-not= -1 (open-cell board :row 1 :col 1))
    	(should-not= -1 (open-cell board :row 1 :col 2))
    	(should-not= -1 (open-cell board :row 2 :col 0))
    	(should-not= -1 (open-cell board :row 2 :col 2)))

	(it "should give number of bombs surround cell"
    	(should= 1 (open-cell board :row 1 :col 0))))

(describe "surrounding-cells"

	(it "surrounding-cells of 8 cells for centered cell"
		((should= 8 (surrounding-cells {:row 1 :col 1})))))


(run-specs)