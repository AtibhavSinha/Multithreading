package com.ati.executor;

public class PalindromeTest
{
	public String palindrome(String temp)
	{

		char[] charArray = temp.toCharArray();

		int start = 0;
		int end = charArray.length - 1;

		if (isPalindrome(charArray, start, end))
			return "";
		else
		{
			start++;
			while (start < end)
			{
				if (isPalindrome(charArray, start, end))
				{
					break;
				}
				start++;

			}
			char[] toReturn = new char[start];
			for (int i = 0; i < toReturn.length; i++)
			{
				toReturn[i] = charArray[--start];
				// start--;
			}
			return new String(toReturn);

		}

	}

	public boolean isPalindrome(char[] input, int start, int end)
	{
		boolean isPalindrome = true;
		while (start < end)
		{
			if (input[start] != input[end])
			{
				isPalindrome = false;
				break;
			}
			start++;
			end--;
		}
		return isPalindrome;

	}

	public static void main(String[] args)
	{

		PalindromeTest test = new PalindromeTest();
		System.out.println(test.palindrome("aba"));
		System.out.println(test.palindrome("aaab"));
		System.out.println(test.palindrome("abb"));
		System.out.println(test.palindrome("abcd"));
		System.out.println(test.palindrome("abcde"));
		System.out.println(test.palindrome("abcaba"));
		System.out.println(test.palindrome("system"));

	}

}
