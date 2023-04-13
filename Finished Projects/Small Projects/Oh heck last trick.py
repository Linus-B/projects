from random import randint

class Card:
    def __init__(self, suit, rank):
        self.suit = suit # Clubs, Spades, Hearts, Trump
        self.rank = rank # 0-13
    def __repr__(self):
        return str(self.rank) + self.suit

class Deck:
    def __init__(self):
        deck_list = []
        suit_list = ['c', 's', 'h', 'd']
        for i in range(13):
            for j in range(4):
                deck_list.append(Card(suit_list[j], i % 13))
        self.deck = deck_list

    def __repr__(self):
        representation = ''
        for card in self.deck:
            representation += (str(card) + ' ')
        return representation

    def shuffle(self):
        for i in range(104):
            number = randint(0, 51)
            tmp = self.deck[i % 52]
            self.deck[i % 52] = self.deck[number]
            self.deck[number] = tmp


def won_trick(card_list): #Contains x cards: first is trump suit, next is played card, next is other players
    for card in card_list[2:]:
        if card_list[1].suit == card.suit and card_list[1].rank < card.rank:
            return False
        elif card_list[0].suit != card_list[1].suit and card.suit == card_list[0].suit:
            return False
        elif card.suit == card_list[0].suit and card_list[1].rank < card.rank:
            return False
    return True
        
        

class Game():
    def __init__(self, players = 4):
        self.players = players
        self.deal = Deck()
        self.info = {}
        for i in range(26):
            self.info[i] = [0, 0, 0]

    def play(self):
        self.deal.shuffle()
        is_trump = 0
        if self.deal.deck[1].suit == self.deal.deck[0].suit:
            is_trump = 13
        if won_trick(self.deal.deck[0:(self.players + 1)]):
            self.info[self.deal.deck[1].rank + is_trump][0] += 11
        else:
            self.info[self.deal.deck[1].rank + is_trump][1] += 6
        self.info[self.deal.deck[1].rank + is_trump][2] += 1
                     
    def get_stats(self):
        print("\trank\tBid 1\tBid 0")
        for rank in self.info:
            print("\t  " + str(rank) + "\t " + str(self.info[rank][0]/self.info[rank][2] * 100 // 1 / 100) + "\t " + str(self.info[rank][1]/self.info[rank][2] * 100 // 1 / 100))
        
        




    
