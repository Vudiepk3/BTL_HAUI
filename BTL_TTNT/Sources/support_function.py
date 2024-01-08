from copy import deepcopy

TIME_OUT = 180
'''
//========================//
//        SUPPORTING      //
//        FUNCTIONS       //
//========================//
'''
'''
CONTAINER DỮ LIỆU ĐỂ LƯU TRẠNG TRẠNG THÁI CHO MỖI BƯỚC
'''
class state:
    def __init__(self, board, state_parent, list_check_point):
        '''storage current board and state parent of this state'''
        self.board = board
        self.state_parent = state_parent
        self.cost = 1
        self.heuristic = 0
        self.check_points = deepcopy(list_check_point)


    ''' HÀM ĐỆ QUY ĐỂ QUAY LẠI VỀ ĐẦU TIÊN NẾU TRẠNG THÁI HIỆN TẠI LÀ MỤC TIÊU '''

    '''sử dụng vòng lặp để tìm bảng danh sách từ đầu đến chỉ số này'''


    def get_line(self):
        if self.state_parent is None:
            return [self.board]
        return (self.state_parent).get_line() + [self.board]



    ''' TÍNH TOÁN HÀM HEURISTIC ĐƯỢC SỬ DỤNG CHO THUẬT TOÁN A* '''



    def compute_heuristic(self):
        list_boxes = find_boxes_position(self.board)
        if self.heuristic == 0:
            self.heuristic = self.cost + abs(sum(list_boxes[i][0] + list_boxes[i][1] - self.check_points[i][0] -
                                                 self.check_points[i][1] for i in range(len(list_boxes))))
        return self.heuristic




    ''' HOẠT ĐỘNG QUÁ TẢI RẰNG CHO PHÉP CÁC BANG ĐƯỢC LƯU TRỮ TRONG HÀNG ƯU TIÊN '''


    def __gt__(self, other):
        if self.compute_heuristic() > other.compute_heuristic():
            return True
        else:
            return False
    def __lt__(self, other):
        if self.compute_heuristic() < other.compute_heuristic():
            return True
        else:
            return False


''' KIỂM TRA BAN CÓ MỤC TIÊU HAY KHÔNG '''
'''trả về true nếu tất cả các điểm kiểm tra được bao phủ bởi các hộp'''

def check_win(board, list_check_point):

    for p in list_check_point:
        if board[p[0]][p[1]] != '$':
            return False
    return True

''' Gán MA TRẬN '''
def assign_matrix(board):
    '''bảng trở lại giống như bảng đầu vào'''
    '''
    for lần lượt từng cái và nhả ra ma trận
    '''
    return [[board[x][y] for y in range(len(board[0]))] for x in range(len(board))]

''' TÌM VỊ TRÍ HIỆN TẠI CỦA NGƯỜI CHƠI TRONG MỘT BAN '''
def find_position_player(board):
    '''trả lại vị trí của người chơi trong bảng'''
    for x in range(len(board)):
        for y in range(len(board[0])):
            '''
        nếu là @ thì trả về tọa độ còn k thì
        '''
            if board[x][y] == '@':
                return (x,y)
    return (-1,-1)  # error board

''' SO SÁNH 2 BAN '''
def compare_matrix(board_A, board_B):
    '''trả về true nếu bảng A giống như bảng B'''
    if len(board_A) != len(board_B) or len(board_A[0]) != len(board_B[0]):
        return False
    for i in range(len(board_A)):
        for j in range(len(board_A[0])):
            if board_A[i][j] != board_B[i][j]:
                return False
    return True

''' KIỂM TRA BAN ĐÃ TỒN TẠI TRONG DANH SÁCH ĐƯỢC CHUYỂN ĐỔI'''
def is_board_exist(board, list_state):
    '''trả về true nếu có cùng một bảng trong danh sách'''
    for state in list_state:
        if compare_matrix(state.board, board): # kiểm tra ma trận tồn tại hay chưa
            return True
    return False

''' KIỂM TRA XEM CÓ MỘT HỘP ĐƠN CÓ TRÊN ĐIỂM KIỂM TRA hay không '''
def is_box_on_check_point(box, list_check_point):
    # ktra một điểm có nằm trong danh sách điểm hay k
    for check_point in list_check_point:
        if box[0] == check_point[0] and box[1] == check_point[1]:
            return True
    return False

''' '''
def check_in_corner(board, x, y, list_check_point):
    '''trả về true nếu board[x][y] ở góc KIỂM TRA XEM HỘP KÝ HIỆU CÓ BỊ KÍCH VÀO GÓC hay không'''
    # ktra xem hộp của mình có nằm trong góc hay k
    if board[x-1][y-1] == '#':
        if board[x-1][y] == '#' and board[x][y-1] == '#': # nếu là dấu #- tường
            # nếu mình thấy thỏa mãn một trong các điều kiện thì mình sẽ k di chuyển nnuwa-> thất bại
            if not is_box_on_check_point((x,y), list_check_point):
                return True
    if board[x+1][y-1] == '#':
        if board[x+1][y] == '#' and board[x][y-1] == '#':
            if not is_box_on_check_point((x,y), list_check_point):
                return True
    if board[x-1][y+1] == '#':
        if board[x-1][y] == '#' and board[x][y+1] == '#':
            if not is_box_on_check_point((x,y), list_check_point):
                return True
    if board[x+1][y+1] == '#':
        if board[x+1][y] == '#' and board[x][y+1] == '#':
            if not is_box_on_check_point((x,y), list_check_point):
                return True
    return False

''' TÌM VỊ TRÍ TẤT CẢ CÁC HỘP '''
def find_boxes_position(board):
    result = []
    for i in range(len(board)):
        for j in range(len(board[0])):
            if board[i][j] == '$':
                result.append((i,j)) # ktra vị trí đã đúng hay chưa
    return result


''' KIỂM TRA XEM CÓ THỂ CHUYỂN ĐỔI MỘT HỘP TRONG ÍT NHẤT 1 HƯỚNG'''
def is_box_can_be_moved(board, box_position):
    # ktra xem hộp có di chuyển hay k
    left_move = (box_position[0], box_position[1] - 1)      # qua trái
    right_move = (box_position[0], box_position[1] + 1)     # qua phải
    up_move = (box_position[0] - 1, box_position[1])        # lên trên
    down_move = (box_position[0] + 1, box_position[1])      # xuống dưới
    if (board[left_move[0]][left_move[1]] == ' ' or board[left_move[0]][left_move[1]] == '%' or board[left_move[0]][left_move[1]] == '@') and board[right_move[0]][right_move[1]] != '#' and board[right_move[0]][right_move[1]] != '$':
        return True
    if (board[right_move[0]][right_move[1]] == ' ' or board[right_move[0]][right_move[1]] == '%' or board[right_move[0]][right_move[1]] == '@') and board[left_move[0]][left_move[1]] != '#' and board[left_move[0]][left_move[1]] != '$':
        return True
    if (board[up_move[0]][up_move[1]] == ' ' or board[up_move[0]][up_move[1]] == '%' or board[up_move[0]][up_move[1]] == '@') and board[down_move[0]][down_move[1]] != '#' and board[down_move[0]][down_move[1]] != '$':
        return True
    if (board[down_move[0]][down_move[1]] == ' ' or board[down_move[0]][down_move[1]] == '%' or board[down_move[0]][down_move[1]] == '@') and board[up_move[0]][up_move[1]] != '#' and board[up_move[0]][up_move[1]] != '$':
        return True
    return False # nếu k di chuyển đc nữa thì sẽ false

''' KIỂM TRA DÒNG TẤT CẢ CÁC HỘP ĐANG BỊ MẮC '''
def is_all_boxes_stuck(board, list_check_point):
    box_positions = find_boxes_position(board)
    result = True
    for box_position in box_positions:
        if is_box_on_check_point(box_position, list_check_point): # list ktra các dấu dona có trùng với dấu % hay chưa
            return False
        if is_box_can_be_moved(board, box_position): # box chưa di chuyển đc thì ...nếu chưa phải thì di chuyển tiếp
            result = False
    return result

''' KIỂM TRA XEM CÓ ÍT NHẤT MỘT HỘP CÓ BỊ KHÓC Ở GÓC không'''
def is_board_can_not_win(board, list_check_point):
    '''trả về true nếu hộp ở góc tường -> không thể thắng'''
    # nếu hộp đẩy vào góc tường thì k đi đc nữa-> false
    for x in range(len(board)):
        for y in range(len(board[0])):
            if board[x][y] == '$':
                if check_in_corner(board, x, y, list_check_point):
                    return True
    return False

''' NHẬN DI CHUYỂN CÓ THỂ TIẾP THEO '''
'''trả về danh sách các vị trí mà người chơi có thể di chuyển đến từ vị trí hiện tại'''


def get_next_pos(board, cur_pos):
    x,y = cur_pos[0], cur_pos[1]
    list_can_move = []
    # MOVE UP (x - 1, y)
    if 0 <= x - 1 < len(board):     # 0<= x-1 < chiều dai ma trận
        value = board[x - 1][y]
        if value == ' ' or value == '%':
            list_can_move.append((x - 1, y))    # append: thêm phần tu vào cuối một list
        elif value == '$' and 0 <= x - 2 < len(board):
            next_pos_box = board[x - 2][y]
            if next_pos_box != '#' and next_pos_box != '$':
                list_can_move.append((x - 1, y))
    # MOVE DOWN (x + 1, y)
    if 0 <= x + 1 < len(board):
        value = board[x + 1][y]
        if value == ' ' or value == '%':
            list_can_move.append((x + 1, y))
        elif value == '$' and 0 <= x + 2 < len(board):
            next_pos_box = board[x + 2][y]
            if next_pos_box != '#' and next_pos_box != '$':
                list_can_move.append((x + 1, y))
    # MOVE LEFT (x, y - 1)
    if 0 <= y - 1 < len(board[0]):
        value = board[x][y - 1]
        if value == ' ' or value == '%':
            list_can_move.append((x, y - 1))
        elif value == '$' and 0 <= y - 2 < len(board[0]):
            next_pos_box = board[x][y - 2]
            if next_pos_box != '#' and next_pos_box != '$':
                list_can_move.append((x, y - 1))
    # MOVE RIGHT (x, y + 1)
    if 0 <= y + 1 < len(board[0]):
        value = board[x][y + 1]
        if value == ' ' or value == '%':
            list_can_move.append((x, y + 1))
        elif value == '$' and 0 <= y + 2 < len(board[0]):
            next_pos_box = board[x][y + 2]
            if next_pos_box != '#' and next_pos_box != '$':
                list_can_move.append((x, y + 1))
    return list_can_move

''' DI CHUYỂN BẢNG THEO CÁC HƯỚNG NHẤT ĐỊNH '''
def move(board, next_pos, cur_pos, list_check_point): # viết lại ma trận mới, mỗi  levelsex đc tạo ra ma trận mới
    '''trả lại một bảng mới sau khi di chuyển'''
    # TẠO BẢNG MỚI GIỐNG NHƯ BẢNG HIỆN TẠI
    new_board = assign_matrix(board) # trả về ma trận, copy ma trận cũ
    # TÌM VỊ TRÍ TIẾP THEO NẾU CHUYỂN ĐẾN HỘP
    #
    if new_board[next_pos[0]][next_pos[1]] == '$':
        x = 2*next_pos[0] - cur_pos[0]
        y = 2*next_pos[1] - cur_pos[1]
        new_board[x][y] = '$'
    # DI CHUYỂN NGƯỜI CHƠI ĐẾN VỊ TRÍ MỚI
    new_board[next_pos[0]][next_pos[1]] = '@'
    new_board[cur_pos[0]][cur_pos[1]] = ' '
    # KIỂM TRA NẾU TẠI VỊ TRÍ ĐIỂM KIỂM TRA KHÔNG CÓ GÌ THÌ CẬP NHẬT % LIKE ĐIỂM KIỂM TRA
    for p in list_check_point:
        if new_board[p[0]][p[1]] == ' ':
            new_board[p[0]][p[1]] = '%'
    return new_board

'''  TÌM TẤT CẢ CÁC ĐIỂM KIỂM TRA TRÊN BẢNG'''
def find_list_check_point(board):
    '''danh sách trả lại điểm kiểm tra từ bảng
        nếu không có bất kỳ điểm kiểm tra nào, hãy trả về danh sách trống
        nó sẽ kiểm tra số hộp, nếu số hộp < số điểm kiểm tra
            danh sách trả về [(-1,-1)]'''
    list_check_point = []
    num_of_box = 0
    ''' KIỂM TRA TOÀN BỘ BẢNG ĐỂ TÌM ĐIỂM KIỂM TRA VÀ SỐ HỘP'''
    for x in range(len(board)):
        for y in range(len(board[0])):
            if board[x][y] == '$':
                num_of_box += 1
            elif board[x][y] == '%':
                list_check_point.append((x,y)) # append vị trí
    ''' KIỂM TRA NẾU SỐ HỘP < SỐ ĐIỂM KIỂM TRA'''
    if num_of_box < len(list_check_point): # ktra có đủ thùng hay k
        return [(-1,-1)]
    return list_check_point

