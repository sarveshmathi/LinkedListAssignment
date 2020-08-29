

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {
	
	/* 
	 * Implement this method!
	 */
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> htmlStack = new Stack<>();
		
		while (!tags.isEmpty()) {
			
			HtmlTag tagPeek = tags.peek();
			
			if(tagPeek.isSelfClosing()) {
				tags.remove();
			} else if (tagPeek.isOpenTag()) {
				htmlStack.push(tags.remove());	
			} else {
				if (!htmlStack.isEmpty()) {
					if (tagPeek.matches(htmlStack.peek())) { 
						tags.remove();
						htmlStack.pop();
					} else {
						return htmlStack;
					}
				} else {
					return null;
				}
			}		
		}
		
		return htmlStack;
		
	}
	
	/*
	 * Instructor-provided code:
	 * This function reads an HTML file and outputs its structure as the HtmlTags only.
	 * You do not need to modify this code for this assignment but can use it for your testing!
	 */
	public static Queue<HtmlTag> getTagsFromHtmlFile(String filename) throws IOException {
	     InputStream stream = new FileInputStream(filename);
	     StringBuffer buffer = new StringBuffer();
	     int ch;
	     while ((ch = stream.read()) > 0) {
	         buffer.append((char) ch);
	     }
	     stream.close();
	     String htmlFileString = buffer.toString();
	     return HtmlTag.tokenize(htmlFileString);
	}
	
	public static void main(String[] args) {
		try {
			Queue<HtmlTag> tags = HtmlValidator.getTagsFromHtmlFile("sample.html");
			System.out.println(HtmlValidator.isValidHtml(tags).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

