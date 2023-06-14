public class MyString implements MyComparable
{
	private char[] letters;
	final int NOT_FOUND = -1;

	public MyString( String other )
	{
		letters = other.toCharArray();
	}

	public MyString( MyString other )
	{
		this( other.toString() );
	}

	public String toString()
	{
		return new String(letters);
	}

	public int length()
	{
		return letters.length;
	}

	public char charAt(int index)
	{
		return letters[index];
	}

///////////////// Y O U    M U S T    W R I T E    T H E S E    T W O    M E T H O D S //////////////

	//RETURNS 0 if strings are lexically identical in every way, +1 if this string greater, else -1
	public int myCompareTo(MyString other)
	{
		int x=0;
		for (int i = 0; i < this.length() && i < other.length(); i++) {
            if ((int)this.charAt(i) == (int)other.charAt(i)) {
                continue;
            }
            else {
                 x = (int)this.charAt(i) - (int)other.charAt(i);
				 break;
            }
        }

        if (x == 0 && this.length() != other.length()) {
             x= (this.length()-other.length());
        }
		if(x>0){
			x=1;
		}else if(x<0){
			x=-1;
		}
	return x;
	}

	//RETURNS 0 iff strings are lexically identical
	public boolean equals(MyString other)
	{
		//if myCompareTo(??) returns 0, return true, else return false;
		return this.myCompareTo(other)==0; //just to make it compile
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////

} // END MYSTRING CLASS