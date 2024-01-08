import support_function as spf
import pygame, sys, random
from queue import PriorityQueue

import main

WHITE =(255,0,0)
score = 0
def Player(board, list_check_point, pygame):
    global score
    if spf.check_win(board, list_check_point):
        print("Found win")
        return True
    start_state = spf.state(board, None, list_check_point)
    list_state = [start_state]
    s_queue = PriorityQueue()
    s_queue.put(start_state)
    while not s_queue.empty():
        now_state = s_queue.get()
        cur_pos = spf.find_position_player(now_state.board)
        list_can_move = spf.get_next_pos(now_state.board, cur_pos)
        new_board = board
        next_pos = cur_pos
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if event.type == pygame.KEYDOWN:
                score += 1
                if event.key == pygame.K_UP:
                    next_pos = [cur_pos[0] - 1,cur_pos[1]]

                if event.key == pygame.K_DOWN:
                    next_pos = [cur_pos[0] + 1, cur_pos[1]]

                if event.key == pygame.K_LEFT:
                    next_pos = [cur_pos[0], cur_pos[1] - 1]

                if event.key == pygame.K_RIGHT:
                    next_pos = [cur_pos[0], cur_pos[1] + 1]

                if (check_map(list_can_move, next_pos) == 1):
                    new_board = spf.move(now_state.board, next_pos, cur_pos, list_check_point)
                    return new_board
                new_state = spf.state(new_board, now_state, list_check_point)
                if spf.check_win(new_board, list_check_point):
                    print("Found win")
                    return True
                list_state.append(new_state)
                s_queue.put(new_state)

                if event.key == pygame.K_SPACE:
                    main.sceneState = "init"
                    main.initGame(board)
                    return board

        pygame.display.update()

    return new_board

def check_map(list_can_move, move):
    isMove = 0
    for next_pos in list_can_move:
        if(move[0] == next_pos[0] and move[1] == next_pos[1]):
            isMove = 1
    return isMove
