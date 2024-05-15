package week15.day3;

import java.util.ArrayList;
import java.util.List;
//TC - O(n ^l)
//SC - O(n)
class TrieNode {
	TrieNode[] children;
	List<String> list;

	public TrieNode() {
		this.children = new TrieNode[26];
		this.list = new ArrayList<>(); // to store list of words in every trie node
	}
}

public class WordSquare {
	public void insertIntoTrieNode(TrieNode root, String word) { // root and word will be given here
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (curr.children[ch - 'a'] == null) {
				curr.children[ch - 'a'] = new TrieNode();
			}
			curr = curr.children[ch - 'a'];
			curr.list.add(word);
		}
	}

	public List<String> searchFromTrieNode(TrieNode root, String prefix) {
		TrieNode curr = root;
		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			if (curr.children[ch - 'a'] == null) {
				return new ArrayList<>();
			}
			curr = curr.children[ch - 'a'];
		}
		return curr.list;
	}

	List<List<String>> result;

	public List<List<String>> wordSquares(String[] words) {
		// insert all words into TrieNode
		TrieNode root = new TrieNode();
		for (int i = 0; i < words.length; i++) {
			insertIntoTrieNode(root, words[i]); //N*l
		}

		this.result = new ArrayList<>();
		List<String> li = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			li.add(words[i]); // action
			backtrack(li, root); // recurse
			li.remove(li.size() - 1);// backtrack
		}

		return result;
	}

	private void backtrack(List<String> li, TrieNode root) {
		// base condition
		if (li.size() == li.get(0).length()) {
			result.add(new ArrayList<>(li));
			return;
		}
		// logic
		// go over each word in list and build prefix
		int idx = li.size(); // b a l l - idx = 1 to get a
		StringBuilder prefix = new StringBuilder();
		// SAY LIST HAS 3 WORDS, GO OVER EACH WORD IN LIST AND fetch 3rd idx, add it to prefix
		for (String word : li) { 
			prefix.append(word.charAt(idx));
		}
		List<String> possibleWords = searchFromTrieNode(root, prefix.toString());
		for (String w : possibleWords) { // SAY LIST HAS 3 WORDS, GO OVER EACH WORD IN LIST AND fetch 3rd idx, add it to
											// prefix
			li.add(w); // action
			backtrack(li, root); // recurse
			li.remove(li.size() - 1);// backtrack
		}
	}

	public static void main(String[] args) {
		// Example usage
		WordSquare w = new WordSquare();
		String[] words = { "area", "lead", "wall", "lady", "ball", "lean","land" };
		List<List<String>> result = w.wordSquares(words);

		// Print the result
		for (List<String> square : result) {
			System.out.println(square);
		}
	}

}
