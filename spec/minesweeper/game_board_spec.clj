(ns minesweeper.game-board-spec
	(:require [speclj.core :refer :all])
	(:require [minesweeper.game-board :refer :all]))


(describe "get-board"

	(it "get-board gives board"
    	(should= [[1 1][1 -1]] (get-board))))


(describe "open-cell"

	(it "opens cell of given id & gives its content"
    	(should= 1 (open-cell {:row 0 :col 0}))
    	(should= -1 (open-cell {:row 1 :col 1}))))


(run-specs)