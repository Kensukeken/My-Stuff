#include <string>
#include <iostream>
#include <vector>
#include <cstdlib>
#include <time.h>
#include <algorithm>
using namespace std;

struct Client
{
    string name;
    int bookingnumb;
    int nightiez;
    int roomnumb;
};

vector<Client> client(400);
// vectors are basically arrays that can change their size => in this program im able to store clients' bookings
// the amount of variables must be inside the brackets.

struct Room
{
    int number;
    int type;
    float price;
    bool free;
    string Pokemon;
    Client client;
};

// Functions for the program to make it easier to understand what's going on

void program(vector<Room>&, vector<Client>&);                         
int IchooseYouPikachu();                                               
int checkAvailability(vector<Room>);                                  
void whatwillPikachudo(vector<Room>&, vector<Client>&, int, int);      
void bookings(vector<Room>&, vector<Client>&, int);                   
bool available(vector<Room>, int);                                    
bool typeAvailability(vector<Room>, int);                             
bool booked(vector<Room>);                                             
int choosetype(vector<Room>);                                         
int roomselection();                                                  
int randomsandomroomiewoomie(vector<Room>&, int);                     
int clientchoosestomanuallyselecttheroom(vector<Room>&, int);          
string whatPokemon();                                                  
void istheroomfreeornauwr(vector<Room>);                               
float discountchecker(Room, Client);                                   
float calculatetheprice(float, int, float);                           
void checkout(vector<Room>&, vector<Client>&);                         
void Invoice(vector<Room>, vector<Client>, int);                      

vector<Room> hotel(400);

void Invoice(vector<Room> hotel, vector<Client> client, int room);

void errorMessage()
{
    cout << "Error! input.exe not found. \nPlease, try to do that properly" << endl;
    cin.clear();
    cin.ignore(256, '\n');
}


bool booked(vector<Room> hotel)
{
    int istheroomtaken = 0;
    // is hotel booked or not
    for (int i = 0; i < hotel.size(); i++)
    {
        // loop, checking every room if its booked
        if (hotel[i].free == false)
        {
            istheroomtaken++;
        }
    }
    if (istheroomtaken == 0)
        return true;
    // if the result is 0 = hotel's not booked
    return false;
}

bool available(vector<Room> hotel, int roomnumero)
{ // returns true or false if the room is available
    if (hotel[roomnumero - 1].free)
        return true;
    return false;
}

int checkAvailability(vector<Room> hotel)
{
    // returns the number of rooms available and if 0, then the hotel is booked uwu
    int availability = 0;
    for (int i = 0; i < hotel.size(); i++)
    {
        if (hotel[i].free == true)
        {
            availability++;
        }
    }
    return availability;
}

float discountchecker(Room room, Client client)
{
   



    float discount;

    if (client.nightiez >= 10)
    {
        discount = 0.1;
    }
    else if (client.nightiez >= 5)
    {
        discount = 0.05;
    }
    else if (client.nightiez < 5)
    {
        return 0;
    }

    if (room.type == 2)
    {
        return (discount * 2);
    }

    return discount;
}

float calculatetheprice(float price, float discount, int nights)
{
    float discountedPrice;

    discountedPrice = price * (static_cast<float>(nights)) * (1.0 - discount);

    return discountedPrice;
}

bool typeAvailability(vector<Room> hotel, int input)
{

    int checkRoomAvailability = 0;

    if (input == 1)
    {
        for (int x = 0; x < hotel.size(); x += 2)
        {
            if (hotel[x].free)
            {
                checkRoomAvailability++;
            }
        }
    }

    if (input == 2)
    {
        for (int x = 1; x < hotel.size(); x += 2)
        {
            if (hotel[x].free)
            {
                checkRoomAvailability++;
            }
        }
    }
    // u know.. yea it does two things thing
    if (checkRoomAvailability == 0)
    {
        return false;
    }

    return true;
}

int choosetype(vector<Room> hotel)
{ 
    int input;
    int yezornauwr;
   

    do
    {
        cout << "What room would you like to have: a single room or a double room?" << endl 
            << "Choose 1 for single room \nChoose 2 for double room." << endl
            << "Choose 0 to DEGUGHI (EXIT)." << endl;

        cin >> input;
        while (cin.fail())
        {
           
            errorMessage();
            cin >> input;
        }

        if (input == 0)
        {
            return 0;
        }

    } while (input != 1 && input != 2);
    
    return input;
}

int roomselection()
{
    int input;
    do
    {
        cout << "Would you like to your room to be selected by the program or would you like to choose it yourself? \n Press 1 if you would like to decide yourself, \n Press 2  for automatic selection " << endl;
        cin >> input;
        while (cin.fail())
        {
            errorMessage();
            cin >> input;
        }
    } while (input != 1 && input != 2);

    return input;
}


int randomsandomroomiewoomie(vector<Room>& hotel, int type)
{ 
    int roomnbr;
    if (type == 1)
    {
        do
        {
            roomnbr = 2 * (rand() % hotel.size() / 2) + 1;
        } while (!(available(hotel, roomnbr)));
    }
    else if (type == 2)
    {
        do
        {
            roomnbr = 2 * (rand() % hotel.size() / 2 + 1);
        } while (!(available(hotel, roomnbr)));
    }

    cout << "Your room number is: " << roomnbr << endl;
    return roomnbr;
}

int clientchoosestomanuallyselecttheroom(vector<Room>& hotel, int type)
{
    int roomnumb;
   
    do
    {
        if (type == 1)
        {
            do
            {
                cout << "Single rooms are odd numbered" << endl
                    << "Which will you book?" << endl;
                cin >> roomnumb;
                while (cin.fail())
                { // cin.fail works the same way as in before
                    errorMessage();
                    cin >> roomnumb;
                }
            } while (roomnumb <= 0 && printf("Error! wrong number, please choose again.\n") || roomnumb % 2 == 0 && printf("Error! wrong number, please choose again.\n") || roomnumb > (hotel.size() + 1) && printf("Error! wrong number, please choose again.\n"));
           
        }
        else if (type == 2)
        {
            do
            {
                cout << "Double rooms are even numbered" << endl
                    << "Which room will you book?" << endl;
                cin >> roomnumb;
                while (cin.fail())
                {
                    errorMessage();
                    cin >> roomnumb;
                }
            } while (roomnumb <= 0 && printf("Error! wrong number, please choose again.\n") || roomnumb % 2 != 0 && printf("Error! wrong number, please choose again.\n") || roomnumb > (hotel.size() + 1) && printf("Error! wrong number, please choose again.\n"));
            
        }
    } while (!(available(hotel, roomnumb)) && printf("Unfortunately this room is not available, please choose another room.\n"));

    return roomnumb;
}

string whatPokemon()
{
    int selectedPokemon;
    string selectedPokemonString;
    do
    {
        cout << "What Pokemon theme would you like to choose for your room? (Enter the number)" << endl
            << "1. Pikachu." << endl
            << "2. Snorlax." << endl
            << "3. Eevee." << endl
            << "4. Clefairy." << endl;
        cin >> selectedPokemon;
        while (cin.fail())
        {
            errorMessage();
            cin >> selectedPokemon;
        }
    } while (selectedPokemon < 0 || selectedPokemon > 4);
    if (selectedPokemon == 1)
    {
        selectedPokemonString = "Pikachu";
    }
    else if (selectedPokemon == 2)
    {
        selectedPokemonString = "Snorlax";
    }
    else if (selectedPokemon == 3)
    {
        selectedPokemonString = "Eevee";
    }
    else
    {
        selectedPokemonString = "Clefairy";
    }
    return selectedPokemonString;

}

void bookings(vector<Room>& hotel, vector<Client>& client, int clientnumb)
{

    string name;
    int nights;
    int type;
    int roomnumb;
    int yezornnauwr;
    int available = 0;
    string selectedPokemon;
    
    type = choosetype(hotel);

    if (type == 0)
    {  
        return;
    }

    int roomselectionz = roomselection();

    switch (roomselectionz)
    {
    case 1:
        roomnumb = clientchoosestomanuallyselecttheroom(hotel, type);
        break;
    case 2:
        roomnumb = randomsandomroomiewoomie(hotel, type);
        break;
    }
    selectedPokemon = whatPokemon();
     
    do
    {
        cout << "How long will you be staying in our hotel?" << endl;
        cin >> nights;
        while (cin.fail())
        {
            errorMessage();
            cin >> nights;
        }
    } while (nights < 1 && printf("Unfortunately it is not possible to stay less than 1 night, please try again, thank you.\n") || nights > 300 && printf("Too many nights lool, just buy a house instead :)\n"));

     
    cout << "Write your full name, please." << endl;
    cin.ignore();
    getline(cin, name);
     

    srand((unsigned int)time(NULL)); // database basically
    client[clientnumb].bookingnumb = rand() % 89999 + 10000;
    client[clientnumb].name = name;
    client[clientnumb].roomnumb = roomnumb;
    client[clientnumb].nightiez = nights;

    hotel[roomnumb - 1].client = client[clientnumb];
    hotel[roomnumb - 1].free = false;
    hotel[roomnumb - 1].Pokemon = selectedPokemon;

    cout << endl
        << "Thank you, " << hotel[roomnumb - 1].client.name << ", Your room number is " << roomnumb << "\nYour booking number is " << hotel[roomnumb - 1].client.bookingnumb << ". You'll need to remember this for checkout!" << endl
        << endl;
}

void istheroomfreeornauwr(vector<Room> hotel)
{  
    int numberfortheroom;

    do
    {
        cout << "Please enter the number of the room: (Enter 0 to exit)" << endl;
        cin >> numberfortheroom;
        while (cin.fail())
        {
            cout << "Error! input.exe not found. \nPlease, choose a number from 1 to " << hotel.size() << ": ";
             
            cin.clear();
            cin.ignore(256, '\n');
            cin >> numberfortheroom;
        }

        if (numberfortheroom == 0)
            return;

    } while (numberfortheroom < 1 || numberfortheroom > hotel.size());

    if (available(hotel, numberfortheroom))
        cout << endl
        << "Room number " << numberfortheroom << " is available." << endl
        << endl;
    else if (!available(hotel, numberfortheroom))
        cout << endl
        << "Unfortunately room number " << numberfortheroom << " is not available." << endl
        << endl;
}

void checkout(vector<Room>& hotel, vector<Client>& client)
 


{
    int roomnumb;
    int bookingnumb;

    cout << "To be able to continue your check out, please enter your booking number and room number. (0 to go back)" << endl;
    do
    {
        cout << "Enter your booking number:" << endl;
        cin >> bookingnumb;
        while (cin.fail())
        {
            errorMessage();
            cin >> bookingnumb;
        }

        if (bookingnumb == 0)
            return;

    } while (bookingnumb < 1);

    do
    {
        cout << "Enter your room number:" << endl;
        cin >> roomnumb;
        while (cin.fail())
        {
            cout << "Error! input.exe not found. \nPlease, choose a number from 1 to " << hotel.size() << ": ";
             
            cin.clear();
            cin.ignore(256, '\n');
            cin >> roomnumb;
        }

        if (roomnumb == 0)
            return;

    } while (roomnumb < 1 || roomnumb > hotel.size());

     
    if (hotel[roomnumb - 1].free)
    {
        cout << endl
            << "This room is available" << endl
            << endl;
    }
    else if (hotel[roomnumb - 1].client.bookingnumb == bookingnumb)
         
    {

        Invoice(hotel, client, roomnumb);

        client.erase(  
            remove_if(client.begin(), client.end(), [&](Client const& x)
                { return x.roomnumb == roomnumb; }),
            client.end());
        client.push_back(Client());  
        hotel[roomnumb - 1].free = true;

        cout << endl
            << "Thank you for choosing MIMARU Tokyo, hope you enjoyed the stay.\n"
            << endl;
    }
    else
    {
        cout << endl
            << "ERROR: the booking number you entered for the room doesn't match with system.\n"
            << "~ Returns ~." << endl
            << endl;
    }
}

void Invoice(vector<Room> hotel, vector<Client> client, int room)
{
     
    float discount = discountchecker(hotel[room - 1], hotel[room - 1].client);
    float tehprice = hotel[room - 1].price * (static_cast<float>(hotel[room - 1].client.nightiez));

    if (hotel[room - 1].free)
    {
        cout << endl
            << "No one is staying in that room." << endl;
         
        return;
    }
     
    cout << "\n--------------------------------------------------" << endl
        << "INVOICE \nMIMARU Tokyo Shinjuku West\n3-chome-3-11 Nishishinjuku City \nTokyo 160-0023, Japan" << endl
        << "\nPayer's name: " << hotel[room - 1].client.name << "     Booking number: " << hotel[room - 1].client.bookingnumb << endl
        << "Room: " << room << "\nRoom type: " << hotel[room - 1].type << "\nNights: " << hotel[room - 1].client.nightiez << "\nPokemon theme: " << hotel[room - 1].Pokemon
        << endl
        << endl
        << "PRICE BEFORE DISCOUNT: $" << tehprice
        << endl
        << "DISCOUNT: " << discount * 100 << "%"
        << endl
        << "TOTAL: $" << tehprice - (discount * tehprice)
        << endl
        << "------------------------------------------------------" << endl;
}

int IchooseYouPikachu()
{
    
    int choice;
    do
    {
        cout << "What would you like to do? (Enter the number)" << endl
            << "1. Book a room." << endl
            << "2. Find a room." << endl
            << "3. Checkout." << endl
            << "4. Invoice." << endl
            << "0. Deguchi (EXIT)." << endl;
        cin >> choice;
        while (cin.fail())
        {
            errorMessage();
            IchooseYouPikachu();
        }
    } while (choice < 0 || choice > 4);
     
    return choice;
}

void whatwillPikachudo(vector<Room>& hotel, vector<Client>& client, int thechosenone, int clientnumb)
{
    int roomnumb;

    switch (thechosenone)
    {
    case 1:
        if (checkAvailability(hotel) == 0)
        {
            cout << "Unfortunately the hotel is fully booked at the moment, we hope to see you again." << endl
                << endl;
            break;
        }
        else
        {
            bookings(hotel, client, clientnumb);
            break;
        }
    case 2:
        istheroomfreeornauwr(hotel);
        break;
    case 3:
        checkout(hotel, client);
        break;
    case 4:
        if (booked(hotel))
        {
            cout << "No one is staying in the hotel at the moment so, unfortunately invoices can't be printed.\n";
            break;
        }

        do
        {
            cout << "Enter the room number, please: (Press 0 to go back)\n";
            cin >> roomnumb;
            while (cin.fail())
            {
                errorMessage();
                cin >> roomnumb;
            }
        } while (roomnumb < 0 || roomnumb > hotel.size());

        if (roomnumb == 0)
        {
            break;
        }

        Invoice(hotel, client, roomnumb);
        break;
    }
}

void program(vector<Room>& hotel, vector<Client>& client)
{
    
    int IchooseYou;
    int clientnumb = 0;

    cout << endl
        << "Welcome to the MIMARU Tokyo Pokemon Hotel Service where you can stay in a room with Pokemon!" << endl
        << "Our hotel has " << hotel.size() << " Pokemon themed rooms and our hotel offers single and double rooms." << endl
        << "Our prices are: \n$100 for a single room per night and \n150$ for a double room per night." << endl
        << endl;

    
    while (true)
    {
        if (checkAvailability(hotel) != 0)
            IchooseYou = IchooseYouPikachu();

       
        if (checkAvailability(hotel) == 0)
        {
            cout << "Unfortunately the hotel is fully booked at the moment, we hope to see you again." << endl;
            break;
        }
        if (IchooseYou == 0)
            break;

        whatwillPikachudo(hotel, client, IchooseYou, clientnumb);

        
        clientnumb++;
    }
}

int main()
{
    

    srand((unsigned int)time(NULL));
     
    int randomroomcalc = 2 * (rand() % 20 + 20);
     

    for (int i = 0; i < randomroomcalc; i++)
    {
         .push_back(Room());
         .push_back(Client());
       

        hotel[i].number = i + 1;
        if (hotel[i].number % 2 == 0)
        {
            
            hotel[i].type = 2;
            hotel[i].price = 150;
            hotel[i].free = true;
        }
        else
        {
            
            hotel[i].type = 1;
            hotel[i].price = 100;
            hotel[i].free = true;
        }
    }

    program(hotel, client);
    

    cout << "Thank you for visiting! We hope to see you again." << endl;
    return 0;
}
