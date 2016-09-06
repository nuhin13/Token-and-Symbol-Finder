#Token and Symbol Finder
	Input: 
			int main()
			{
				int a,b,sum;
				sum=a+b;
				return 0;
			}
	Output:
									Tokens:

			Lexemes			Token Name			Attribute Value
			-----------------------------------------------------------------------------------
			int			int			
			main			id				0

			(			special symbol			Opening braces
			)			special symbol			Closing braces
			{			special symbol			Left curly braces
			int			int			
			a			id				1

			,			special symbol			Comma
			b			id				2

			,			special symbol			Comma
			sum			id				3

			;			special symbol			Semicolon
			sum			id				3

			=			operator			Assignment
			a			id				1

			+			operator			Addition
			b			id				2

			;			special symbol			Semicolon
			return			return			
			0			number				Constant

			;			special symbol			Semicolon
			}			special symbol			Right curly braces








								Symbol Table:

			Symbol			Data Type		Token		Pointer to Symbol Table Entry
			-------------------------------------------------------------------------------------------------
			main						id			0
			a			int			id			1
			b			int			id			2
			sum			int			id			3
