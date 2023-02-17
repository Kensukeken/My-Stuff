using System;
using System.IO;
using System.Text;

namespace SecureMsg
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Welcome to the SecureMsg!");

            while (true)
            {
                Console.Write("Enter a message to encode (or 'q' to quit): ");
                string input = Console.ReadLine();

                if (input == "q")
                {
                    break;
                }

                string encoded = EncodeMessage(input);
                Console.WriteLine("Encoded message: " + encoded);

                string decoded = DecodeMessage(encoded);
                Console.WriteLine("Decoded message: " + decoded);
            }

            Console.WriteLine("Thanks for using the SecureMsg!");
        }

        static string EncodeMessage(string message)
        {
            byte[] bytes = Encoding.ASCII.GetBytes(message);
            for (int i = 0; i < bytes.Length; i++)
            {
                bytes[i] += 1;
            }
            return Encoding.ASCII.GetString(bytes);
        }

        static string DecodeMessage(string message)
        {
            byte[] bytes = Encoding.ASCII.GetBytes(message);
            for (int i = 0; i < bytes.Length; i++)
            {
                bytes[i] -= 1;
            }
            return Encoding.ASCII.GetString(bytes);
        }
    }
}
