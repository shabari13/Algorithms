package neetcode250.stack;

import java.util.Stack;

/*
 * Simplify Path
Medium
Topics
Company Tags
You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.

The rules of a Unix-style file system are as follows:

A single period '.' represents the current directory.
A double period '..' represents the previous/parent directory.
Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
The simplified canonical path should follow these rules:

The path must start with a single slash '/'.
Directories within the path must be separated by exactly one slash '/'.
The path must not end with a slash '/', unless it is the root directory.
The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
Return the simplified canonical path.

Example 1:

Input: path = "/neetcode/practice//...///../courses"

Output: "/neetcode/practice/courses"
Example 2:

Input: path = "/..//"

Output: "/"
Example 3:

Input: path = "/..//_home/a/b/..///"

Output: "/_home/a"
Constraints:

1 <= path.length <= 3000
path consists of English letters, digits, period '.', slash '/' or '_'.
path is a valid absolute Unix path.

The core idea: A Unix path can have . (stay in current dir), .. (go up one dir), empty strings (from double slashes), or valid directory names. We use a deque (double-ended queue) acting as a stack — push valid directory names onto it, pop when we see .., and ignore . or empty parts. At the end, join everything with / to form the simplified canonical path.
Like you're 5: Imagine you're in a house with many rooms, and someone gives you directions like "Go to kitchen, then go back (..),  then go to bathroom, then stay where you are (.)". You follow each instruction one by one. If told to "go back", you step out of the room you're in. If told to "stay", you don't move. At the end, you look behind you and name all the rooms you walked through in order — that's your final path!
Time — O(n), where n is the length of the input string. Splitting the string into parts takes O(n). We then iterate through each part exactly once — each part is either skipped, pushed, or popped in O(1). Building the final string also takes O(n) in the worst case. So the total is linear.
Space — O(n) in the worst case. The stack can hold at most O(n/2) entries (e.g. a path like /a/b/c/d/e with no ..), and the split() array also uses O(n) space. 
The StringBuilder for the result is also O(n). Everything is proportional to the input size.
 */
public class SimplifyPath {
	
	public String simplifyPath(String path) {
		String[] parts = path.split("/");
		Stack<String> stack = new Stack<>();
		for(String part : parts) {
			if(part.equals("") || part.equals(".")) {
				continue;
			} else if(part.equals("..")) {
				if(!stack.isEmpty()) {
					stack.pop();
				}
			} else {
				stack.push(part);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(String dir : stack) {
			sb.append("/");
			sb.append(dir);
		}
		return sb.toString().length() == 0 ? "/" : sb.toString();
	}

}
