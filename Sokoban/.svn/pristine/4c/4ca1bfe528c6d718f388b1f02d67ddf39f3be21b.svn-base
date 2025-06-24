class Grille:
    
    def __init__(self,nbl,nbc,init=0,sep='\t',nord=0,est=1,sud=2,ouest=3):
        self.nbl = nbl
        self.nbc = nbc
        self.sep = sep
        self.nord = nord
        self.est = est
        self.sud = sud
        self.ouest = ouest
        self.grille = [[init] * nbc for _ in range(nbl)]
        
    def __repr__(self):
        res = ''
        for ligne in self.grille:
            res += '\n|'+ self.sep + self.sep.join(str(val) for val in ligne) + self.sep + '|'
        return res

    def __add__(self, other):
        res = Grille(self.nbl, self.nbc)
        for i in range(self.nbl):
            for j in range(self.nbc):
                res.grille[i][j] = self.grille[i][j] + other.grille[i][j]
        return res

    def __len__(self):
        return self.nbl * self.nbc

    def lc_to_case(self, i, j):
        return i * self.nbc + j

    def case_to_lc(self, num_case):
        return num_case // self.nbc, num_case % self.nbc

    def get_case(self, num_case):
        l, c = self.case_to_lc(num_case)
        return self.grille[l][c]

    def set_case(self, num_case, val):
        l, c = self.case_to_lc(num_case)
        self.grille[l][c] = val

    def cases(self, symb):
        return [i for i in range(self.nbl * self.nbc) if self.get_case(i) == symb]

    def lig_col_next(self, lig, col, direction, tore=False):
        new_lig, new_col = lig, col
        if direction == self.nord:
            if not tore and lig == 0:
                return -1
            new_lig, new_col = (lig - 1) % self.nbl, col
        if direction == self.est:
            if not tore and col == self.nbc - 1:
                return -1
            new_lig, new_col = lig, (col + 1) % self.nbc
        if direction == self.sud:
            if not tore and lig == self.nbl - 1:
                return -1
            new_lig, new_col = (lig + 1) % self.nbl, col
        if direction == self.ouest:
            if not tore and col == 0:
                return -1
            new_lig, new_col = lig, (col - 1) % self.nbc
        return new_lig, new_col

    def num_case_next(self, num_case, direction, tore=False):
        lig, col = self.case_to_lc(num_case)
        val = self.lig_col_next(lig, col, direction, tore)
        if val == -1:
            return val
        return self.lc_to_case(val[0], val[1])

class LevelSok(Grille):

    def __init__(self, file):
        self.file = file
        with open(file, 'r', encoding='utf-8') as f:
            self.temp = []
            ligne = f.readline()[:-1]
            while '#' in ligne:
                self.temp.append(list(ligne))
                ligne = f.readline()[:-1]
            nbl = len(self.temp)
            nbc = max(len(ligne) for ligne in self.temp)
        Grille.__init__(self, nbl, nbc, ' ', ' ')
        self.grille = []
        for ligne in self.temp:
            self.grille.append(ligne + [' ']*(nbc-len(ligne)))

    def deplace_man_N(self):
        if len(self.cases('@')) == 1:
            num_man = self.cases('@')
        else:
            num_man = self.cases('+')
        man = self.get_case(num_man[0])
        man_nord = self.num_case_next(num_man[0], 0)
        new_man = self.get_case(man_nord)
        if new_man == '#':
            return 'mur'
        elif new_man == '$':
            self.deplace_caisse_N(man_nord)
        elif new_man == '.':
            self.set_case(man_nord, '+')
            self.set_case(num_man[0], ' ') 
        else:
            if man == '+':
                man = '@'
                new_man = '.'
            self.set_case(man_nord, man)
            self.set_case(num_man[0], new_man)

    def deplace_man_E(self):
        if len(self.cases('@')) == 1:
            num_man = self.cases('@')
        else:
            num_man = self.cases('+')
        man = self.get_case(num_man[0])
        man_est = self.num_case_next(num_man[0], 1)
        new_man = self.get_case(man_est)
        if new_man == '#':
            return 'mur'
        elif new_man == '$':
            self.deplace_caisse_E(man_est)
        elif new_man == '.':
            self.set_case(man_est, '+')
            self.set_case(num_man[0], ' ')
        else:
            if man == '+':
                man = '@'
                new_man = '.'
            self.set_case(man_est, man)
            self.set_case(num_man[0], new_man)

    def deplace_man_S(self):
        if len(self.cases('@')) == 1:
            num_man = self.cases('@')
        else:
            num_man = self.cases('+')
        man = self.get_case(num_man[0])
        man_sud = self.num_case_next(num_man[0], 2)
        new_man = self.get_case(man_sud)
        if new_man == '#':
            return 'mur'
        elif new_man == '$':
            self.deplace_caisse_S(man_sud)
        elif new_man == '*':
            self.deplace_caisse_S(man_sud)
        elif new_man == '.':
            self.set_case(man_sud, '+')
            self.set_case(num_man[0], ' ')
        else:
            if man == '+':
                man = '@'
                new_man = '.'
            self.set_case(man_sud, man)
            self.set_case(num_man[0], new_man)
        
    def deplace_man_O(self):
        if len(self.cases('@')) == 1:
            num_man = self.cases('@')
        else:
            num_man = self.cases('+')
        man = self.get_case(num_man[0])
        man_ouest = self.num_case_next(num_man[0], 3)
        new_man = self.get_case(man_ouest)
        if new_man == '#':
            return 'mur'
        elif new_man == '$':
            self.deplace_caisse_O(man_ouest)
        elif new_man == '.':
            self.set_case(man_ouest, '+')
            self.set_case(num_man[0], ' ')
        else:
            if man == '+':
                man = '@'
                new_man = '.'
            self.set_case(man_ouest, man)
            self.set_case(num_man[0], new_man)

    def deplace_caisse_N(self, num_caisse):
        caisse = self.get_case(num_caisse)
        caisse_nord = self.num_case_next(num_caisse, 0)
        new_caisse = self.get_case(caisse_nord)
        if new_caisse == '#':
            return 'mur'
        elif new_caisse == '.':
            self.destination_N(caisse_nord, num_caisse)
        else:
            self.set_case(caisse_nord, caisse)
            self.set_case(num_caisse, new_caisse)
            self.deplace_man_N()

    def deplace_caisse_E(self, num_caisse):
        caisse = self.get_case(num_caisse)
        caisse_est = self.num_case_next(num_caisse, 1)
        new_caisse = self.get_case(caisse_est)
        if new_caisse == '#':
            return 'mur'
        elif new_caisse == '.':
            self.destination_E(caisse_est, num_caisse)
        else:
            self.set_case(caisse_est, caisse)
            self.set_case(num_caisse, new_caisse)
            self.deplace_man_E()

    def deplace_caisse_S(self, num_caisse):
        caisse = self.get_case(num_caisse)
        caisse_sud = self.num_case_next(num_caisse, 2)
        new_caisse = self.get_case(caisse_sud)
        if new_caisse == '#':
            return 'mur'
        elif new_caisse == '.':
            self.destination_S(caisse_sud, num_caisse)
        else:
            if caisse == '*':
                caisse = '$'
                new_caisse = '.'
            self.set_case(caisse_sud, caisse)
            self.set_case(num_caisse, new_caisse)
            self.deplace_man_S()

    def deplace_caisse_O(self, num_caisse):
        caisse = self.get_case(num_caisse)
        caisse_ouest = self.num_case_next(num_caisse, 3)
        new_caisse = self.get_case(caisse_ouest)
        if new_caisse == '#':
            return 'mur'
        elif new_caisse == '.':
            self.destination_O(caisse_ouest, num_caisse)
        else:
            self.set_case(caisse_ouest, caisse)
            self.set_case(num_caisse, new_caisse)
            self.deplace_man_O()

    def destination_N(self, num_desti, num_caisse):
        self.set_case(num_desti,' ')
        self.deplace_caisse_N(num_caisse)
        self.set_case(num_desti,'*')

    def destination_E(self, num_desti, num_caisse):
        self.set_case(num_desti,' ')
        self.deplace_caisse_E(num_caisse)
        self.set_case(num_desti,'*')

    def destination_S(self, num_desti, num_caisse):
        self.set_case(num_desti,' ')
        self.deplace_caisse_S(num_caisse)
        self.set_case(num_desti,'*')

    def destination_O(self, num_desti, num_caisse):
        self.set_case(num_desti,' ')
        self.deplace_caisse_O(num_caisse)
        self.set_case(num_desti,'*')
    
class Jeu:

    LEVELS = ['niveaux/0001 - POUR COMMENCER.xsb']

    def __init__(self, folder=''):
        self.level = LevelSok(Jeu.LEVELS[0])

    def haut():
        self.level.deplace_man_N()

    def bas():
        self.level.deplace_man_S()

    def gauche():
        self.level.deplace_man_O()

    def droite():
        self.level.deplace_man_E()
    

           
if __name__=="__main__" :
    g = LevelSok('niveaux/0001 - POUR COMMENCER.xsb')
    print(g)
    g.deplace_man_S()
    g.deplace_man_S()
    g.deplace_man_S()
    g.deplace_man_O()
    g.deplace_man_O()
    g.deplace_man_O()
    g.deplace_man_N()
    g.deplace_man_N()
    g.deplace_man_N()
    g.deplace_man_N()
    g.deplace_man_N()
    g.deplace_man_N()
    g.deplace_man_E()
    g.deplace_man_N()
    g.deplace_man_O()
    g.deplace_man_O()
    g.deplace_man_O()
    g.deplace_man_S()
    g.deplace_man_O()
    g.deplace_man_O()
    g.deplace_man_O()
    g.deplace_man_N()
    g.deplace_man_E()
    g.deplace_man_E()
    g.deplace_man_E()
    g.deplace_man_E()
    g.deplace_man_S()
    g.deplace_man_E()
    g.deplace_man_N()
    g.deplace_man_N()
    g.deplace_man_N()
    g.deplace_man_O()
    g.deplace_man_N()
    g.deplace_man_N()
    g.deplace_man_N()
    g.deplace_man_E()
    g.deplace_man_S()
    g.deplace_man_S()
    g.deplace_man_S()
    g.deplace_man_S()
    print(g)
    
    f = LevelSok('niveaux/0001 - POUR COMMENCER.xsb')
    f.set_case(86, '$')
    f.set_case(104, '@')
    f.set_case(79, ' ')
    print(f)
    f.deplace_man_N()
    print(f)
    
    

    
    

