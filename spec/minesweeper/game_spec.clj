(ns minesweeper.game-spec
  (:require [speclj.core :refer :all])
  (:require [minesweeper.game :refer :all]))

(describe "game"
          (before
           (def board-view [ [nil nil nil] [nil nil nil] [nil nil nil] ]))

          (describe "open-cell"
                    (it "opens cell at given location & returns new game"
                        (let [p-open-cell (partial open-cell board-view)]
                          (should= [[-1 nil nil] [nil nil nil] [nil nil nil]] (p-open-cell {:row 0 :col 0}))
                          (should= [[nil 1 nil] [nil nil nil] [nil nil nil]] (p-open-cell {:row 0 :col 1}))
                          (should= [[nil nil 0] [nil nil nil] [nil nil nil]] (p-open-cell {:row 0 :col 2}))

                          (should= [[nil nil nil] [2 nil nil] [nil nil nil]] (p-open-cell {:row 1 :col 0}))
                          (should= [[nil nil nil] [nil 2 nil] [nil nil nil]] (p-open-cell {:row 1 :col 1}))
                          (should= [[nil nil nil] [nil nil 1] [nil nil nil]] (p-open-cell {:row 1 :col 2}))

                          (should= [[nil nil nil] [nil nil nil] [1 nil nil]] (p-open-cell {:row 2 :col 0}))
                          (should= [[nil nil nil] [nil nil nil] [nil -1 nil]] (p-open-cell {:row 2 :col 1}))
                          (should= [[nil nil nil] [nil nil nil] [nil nil 1]] (p-open-cell {:row 2 :col 2}))))

                    (it "should not open-cell when game is over"
                        (should= [[-1 nil nil] [nil nil nil] [nil nil nil]]
                                 (open-cell board-view {:row 0 :col 0}))))

          (describe "update-cell"
                    (it "updates cell at given location & returns new board-view"
                        (should= [[-1 nil nil] [nil nil nil] [nil nil nil]] (update-cell board-view {:row 0 :col 0} -1)))

                    (it "should give back same board-view when cell location is not valid"
                        (should= [[nil nil nil] [nil nil nil] [nil nil nil]] (update-cell board-view {:row 3 :col 3} -1))))

          (describe "get-status"

                    (it "should tell game is not finished & not won"
                        (let [game-status (get-status board-view)]
                          (should= false (:finished? game-status))
                          (should= false (:won? game-status))))

                    (it "should tell game is over on opening bomb cell"
                        (let [board-view (open-cell board-view {:row 0 :col 0})
                              game-status (get-status board-view)]
                          (should= true (:finished? game-status))
                          (should= false (:won? game-status))))

                    (it "should tell you won the game"
                        (let [board-view (open-cell board-view {:row 0 :col 1})
                              board-view (open-cell board-view {:row 0 :col 2})
                              board-view (open-cell board-view {:row 1 :col 0})
                              board-view (open-cell board-view {:row 1 :col 1})
                              board-view (open-cell board-view {:row 1 :col 2})
                              board-view (open-cell board-view {:row 2 :col 0})
                              board-view (open-cell board-view {:row 2 :col 2})
                              game-status (get-status board-view)]
                          (should= true (:finished? game-status))
                          (should= true (:won? game-status))))

                    (it "should tell game is not finished in the middle of game"
                        (let [board-view (open-cell board-view {:row 0 :col 1})
                              game-status (get-status board-view)]
                          (should= false (:finished? game-status))
                          (should= false (:won? game-status)))))

          (describe "did-bomb-blast?"
                    (it "should give true when board has -1"
                        (def board-with-bomb-blast (update-cell board-view {:row 0 :col 1} -1))
                        (should= true (did-bomb-blast? board-with-bomb-blast))

                        (it "should give false when board does not have -1"
                            (should= false (did-bomb-blast? board-view)))))

          (describe "won?"
                    (it "should give true when all safe cells are opened"
                        (should= true (won? [[nil 1 0] [2 2 1] [1 nil 1]])))

                    (it "should give false when all safe cells are not opened"
                        (should= false (won? [[nil 1 0] [2 nil 1] [1 nil 1]])))

                    (it "should give true when all safe cells are opened"
                        (should= false (won? [[-1 nil 0] [2 2 1] [1 nil 1]])))))

(run-specs)