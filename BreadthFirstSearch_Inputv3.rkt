 #lang racket
  (define input(list "ask" "tie" "art" "bad" "ban" "add" "air" "arm" "oaf" "bee" "boa" "aid" "aye" "far" "aim" "fat" "any" "ago" "eel" "awe" "bag" "ash" "eft" "ape" "ado" "awl" "tar" "and" "age" "bat" "lee" "apt" "ear" "arc" "are" "rat" "ark" "fit" "auk" "ail"))




(define (PuzzleSolver StartNode FirstWords SecondWords ThirdWords queue)
  (write StartNode)
  (display "\n")
  (if (HasChildren? StartNode)
      (cond
        [(equal? (length StartNode) 0)(PuzzleSolver
                                       (car (MakeChildren StartNode FirstWords queue))
                                       FirstWords
                                       SecondWords
                                       ThirdWords
                                       (cdr (MakeChildren StartNode FirstWords queue))
                                       ) ]
        [(equal? (length StartNode) 1)(PuzzleSolver
                                       (top (MakeChildren StartNode SecondWords queue))
                                       FirstWords
                                       SecondWords
                                       ThirdWords
                                       (pop (MakeChildren StartNode SecondWords queue))
                                       ) ]
        [(equal? (length StartNode) 2)(PuzzleSolver
                                       (top (MakeChildren StartNode ThirdWords queue))
                                       FirstWords
                                       SecondWords
                                       ThirdWords
                                       (pop (MakeChildren StartNode ThirdWords queue))
                                       ) ]
        )
      (cond
        [(IsGoal? input StartNode) (write StartNode)]
        [else(PuzzleSolver(top queue) FirstWords SecondWords ThirdWords (pop queue))]
        )
      )
  )

(define (MakeChildren ParentNode Words queue)
  (cond
    [(equal? (length Words) 1)(push (flatten(list ParentNode (car Words))) queue)]
    [else(MakeChildren ParentNode (cdr Words)(push (list(append ParentNode (car Words))) queue))]
    )
  
  )

;(define (MakeChildren ParentNode Words queue)
 ; (for ([i Words])
  ;  (push (append ParentNode i) queue)
   ; (display (append ParentNode i))
    ;)
 ; )

(define (Main)
  (define FirstWords (wordFilter input (remove-duplicates (1stLetters input))))
  (define SecondWords (wordFilter input (remove-duplicates (2ndLetters input))))
  (define ThirdWords (wordFilter input (remove-duplicates (3rdLetters input))))
  (PuzzleSolver '() FirstWords SecondWords ThirdWords '(()))
  )

(define (MainDumb)
  (PuzzleSolver '("add" "add" "add") input input input '(("add" "add" "add")))
  )

;(define FirstLetters (remove-duplicates (1stLetters input)))
;(define SecondLetters (remove-duplicates (2ndLetters input)))
;(define ThirdLetters (remove-duplicates (3rdLetters input)))

;(define FirstWords (wordFilter input FirstLetters))
;(define SecondWords (wordFilter input SecondLetters))
;(define ThirdWords (wordFilter input ThirdLetters))




;-----------------------Pre-Search Filters-------------------------


;Creates of list of the starting letters of every word in the given list.
;May include duplicates
(define (1stLetters words)
  (if (empty? words) '()
      (append (1stLetters (cdr words)) (list(string-ref (car words) 0)))
      )
  )

(define (2ndLetters words)
  (if (empty? words) '()
      (append (2ndLetters (cdr words)) (list(string-ref (car words) 1)))
      )
  )

(define (3rdLetters words)
  (if (empty? words) '()
      (append (3rdLetters (cdr words)) (list(string-ref (car words) 2)))
      )
  )


;accepts a list of word and a list of letters and removes words that are
;comprized of letters other than those provided in the list and returns that
;list
(define (wordFilter words letters)
  (if (empty? words) '()
      (if (and (member (string-ref (car words) 0) letters) (and (member(string-ref (car words) 1) letters)  (member (string-ref (car words) 2) letters)))
          (append (list(car words)) (wordFilter (cdr words) letters))
          (wordFilter (cdr words) letters)
          )
      )
  )


;accepts a list of letters and makes a list of letters that were duplicated
;in the old list. The new list still may contain duplicates. This should NOT
;be passed to wordfilter as it is to specific.
(define (LetterMatchFilter letters)
  (if (empty? letters) '()
      (if (member (car letters) (cdr letters))
          (append (list (car letters)) (LetterMatchFilter (cdr letters)))
          (LetterMatchFilter (cdr letters))
          )
      )
  )

;---------------------------Checker Functions-------------------------

(define (HasChildren? Node)
  (not(equal?(length Node) 3))
  )

(define (IsGoal? words Node)
  (if (and
       (member(string (string-ref (list-ref Node 0) 0) (string-ref (list-ref Node 1) 0)(string-ref (list-ref Node 2) 0)) words)
       (and
        (member (string (string-ref (list-ref Node 0) 1) (string-ref (list-ref Node 1) 1)(string-ref (list-ref Node 2) 1)) words)
        (member (string (string-ref (list-ref Node 0) 2) (string-ref (list-ref Node 1) 2)(string-ref (list-ref Node 2) 2)) words)
            )
           )
      #t
      #f
      )
)



;-------------------------Queue Tools-----------------------

;queue tools
(define (empty? queue)
  (null? queue)
  )

(define (push el queue)
  (append queue (list (flatten el)) )
  )

(define (pop queue)
  (if (empty? queue)
      queue
      (cdr queue)
      )
  )

(define (top queue)
  (if (empty? queue)
      '()
      (car queue)
      )
  )


;++++++++++++++++++++++Execution+++++++++++++++++++++++

(Main)