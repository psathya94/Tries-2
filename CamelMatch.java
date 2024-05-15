package week15.day5;

import java.util.ArrayList;
import java.util.List;

//TC - O(n+m)
//SC - O(1)
//https://leetcode.com/problems/camelcase-matching/
class Solution {
	public List<Boolean> camelMatch(String[] queries, String pattern) {
		List<Boolean> list = new ArrayList<>();
		for (int i = 0; i < queries.length; i++) {
			String query = queries[i];
			list.add(isMatch(query, pattern));
		}
		return list;
	}

	public boolean isMatch(String query, String pattern) {
		int j = 0;
		for (int i = 0; i < query.length(); i++) { // queries - FooBarTest, pattern - FoBa
			char q = query.charAt(i);
			if (j < pattern.length() && pattern.charAt(j) == q) {
				j++;
			} else if (Character.isUpperCase(q)) {
				return false;
			}

		}
		return j == pattern.length();
	}
}

public class CamelMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
