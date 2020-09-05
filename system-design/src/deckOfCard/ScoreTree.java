package deckOfCard;

import net.bytebuddy.description.field.FieldDescription;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

class ScoreTree {
    ScoreElement root;
    ScoreTree(TreeSet<Card> cards) {
        root = new ScoreElement(cards.iterator(), 0);
    }

    public int depth() {
        int count = 0;
        ScoreElement target = this.root;
        while(target.left != null) {
            count++;
            target = target.left;
        }
        return count;
    }


    private void collectScore(ScoreElement s, List<Integer> result) {
        if(s.left == null) {
            result.add(s.totalScore);
            return;
        }
        collectScore(s.left, result);
        if(s.right != null) {
            collectScore(s.right, result);
        }
    }

    public int getBestScore() {
        List<Integer> result = new ArrayList();
        collectScore(root, result);
        int bestScore = result.stream()
                .filter(e -> e < 22)
                .max(Integer::compareTo)
                .orElse(0);
        return bestScore;
    }

    static class ScoreElement {
        ScoreElement left;
        ScoreElement right;
        int totalScore = 0;
        ScoreElement(Iterator<Card> c, int currentScore) {
            totalScore = currentScore;
            if(c.hasNext()) {
                Card nextElement = c.next();
                List<Integer> nextScore = evaluate(nextElement);
                if(nextScore.size() == 2) {
                    left = new ScoreElement(c, totalScore + nextScore.get(0));
                    right = new ScoreElement(c, totalScore + nextScore.get(1));
                } else {
                    left = new ScoreElement(c, totalScore + nextScore.get(0));
                }
            }
        }

        private List<Integer> evaluate(Card c) {
            List<Integer> result = new ArrayList();
            if(c.getNumber() > 9) {
                result.add(10);
                return result;
            }
            if(c.getNumber() == 1) {
                result.add(1);
                result.add(11);
                return result;
            }
            result.add(c.getNumber());
            return result;
        }
    }

}

