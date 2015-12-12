(ns minesweeper.game-board-spec
	(:require [speclj.core :refer :all])
	(:require [minesweeper.game-board :refer :all]))


(def ^{:private true} board [[1 1][1 -1]])

(describe "open-cell"

	(it "opens cell of the given id & gives its content"
    	(should= 1 (open-cell board {:row 0 :col 0}))
    	(should= -1 (open-cell board {:row 1 :col 1}))))

(run-specs)