# Welcome
![Logo](https://cdn.discordapp.com/attachments/934212312921931786/1007127092464463902/ezgif.com-gif-maker1.gif)
## My Introduction
Hi, call me Ken/Akira. I'm an artist and I code in TeX, Python, C++ and C#. <br/>
Check out my profile here: https://github.com/Kensukeken

## Usage/Examples
``For C#``
```using System;
using System.Collections.Generic;
using System.Drawing;
using System.Globalization;
using System.Numerics;
using System.Reflection;
using System.Windows.Forms;

using IVSDKDotNet;
using IVSDKDotNet.Enums;
using static IVSDKDotNet.Native.Natives;

namespace IV_MG
{
    public class Main : Script
    {

        #region Variables
        //private int playerPed;
        #endregion

        #region Constructor
        public Main()
        {
            // Subscribe to script events
            Initialized += Main_Initialized;
            Tick += Main_Tick;
            KeyDown += Main_KeyDown;
        }
        #endregion

        private void Main_Initialized(object sender, EventArgs e)
        {

        }

        // Runs every frame when in-game
        private void Main_Tick(object sender, EventArgs e)
        {
            int model, m, m2, m3, m4, m5, m6, m7,
                findmodel = (int)ePed.PED_M_Y_SWAT,
                findmodel2 = (int)ePed.PED_M_Y_COP,
                findmodel3 = (int)ePed.PED_M_Y_COP_TRAFFIC,
                findmodel4 = (int)ePed.PED_M_Y_STROOPER;

            uint model2;
            int
                polpat = (int)eVehicle.VEHICLE_POLPATRIOT,
                noose = (int)eVehicle.VEHICLE_NOOSE,
                police = (int)eVehicle.VEHICLE_POLICE,
                police2 = (int)eVehicle.VEHICLE_POLICE2,
                polmav = (int)eVehicle.VEHICLE_POLMAV,
                pred = (int)eVehicle.VEHICLE_PREDATOR;

            #region PoliceCops
            //PedPools
            int[] peds = CPools.GetAllPedHandles();
            for (int i = 0; i < peds.Length; i++)
            {
                if (DOES_CHAR_EXIST(peds[i]))
                {
                    model = 0;
                    GET_CHAR_MODEL(peds[i], out model);
                    {
                        if (((model != 0) && (findmodel == 0)) || (findmodel == model))
                        {
                            if (!IS_CHAR_DEAD(peds[i]))
                            {
                                if (IS_CHAR_IN_ANY_HELI(peds[i]) && !HAS_CHAR_GOT_WEAPON(peds[i], (int)eWeaponType.WEAPON_KNIFE))
                                {
                                    REMOVE_ALL_CHAR_WEAPONS(peds[i]);
                                    GIVE_WEAPON_TO_CHAR(peds[i], (uint)eWeaponType.WEAPON_EPISODIC_14, 1000, false);
                                    GIVE_WEAPON_TO_CHAR(peds[i], (uint)eWeaponType.WEAPON_KNIFE, 1, false);
                                }
                            }
                        }
                    }
                }
            }
            //PedPools
            int[] peds2 = CPools.GetAllPedHandles();
            for (int i = 0; i < peds2.Length; i++)
            {
                if (DOES_CHAR_EXIST(peds2[i]))
                {
                    m = 0;
                    GET_CHAR_MODEL(peds2[i], out m);
                    {
                        if (((m != 0) && (findmodel2 == 0)) || (findmodel2 == m))
                        {
                            if (!IS_CHAR_DEAD(peds2[i]))
                            {

                                if (IS_CHAR_IN_ANY_HELI(peds2[i]) && !HAS_CHAR_GOT_WEAPON(peds2[i], (int)eWeaponType.WEAPON_KNIFE))
                                {
                                    REMOVE_ALL_CHAR_WEAPONS(peds2[i]);
                                    GIVE_WEAPON_TO_CHAR(peds2[i], (uint)eWeaponType.WEAPON_EPISODIC_14, 1000, false);
                                    GIVE_WEAPON_TO_CHAR(peds2[i], (uint)eWeaponType.WEAPON_KNIFE, 1, false);
                                }
                            }
                        }
                    }
                }
            }
            //PedPools
            int[] peds3 = CPools.GetAllPedHandles();
            for (int i = 0; i < peds3.Length; i++)
            {
                if (DOES_CHAR_EXIST(peds3[i]))
                {
                    m2 = 0;
                    GET_CHAR_MODEL(peds3[i], out m);
                    {
                        if (((m2 != 0) && (findmodel3 == 0)) || (findmodel3 == m2))
                        {
                            if (!IS_CHAR_DEAD(peds3[i]))
                            {

                                if (IS_CHAR_IN_ANY_HELI(peds3[i]) && !HAS_CHAR_GOT_WEAPON(peds3[i], (int)eWeaponType.WEAPON_KNIFE))
                                {
                                    REMOVE_ALL_CHAR_WEAPONS(peds3[i]);
                                    GIVE_WEAPON_TO_CHAR(peds3[i], (uint)eWeaponType.WEAPON_EPISODIC_14, 1000, false);
                                    GIVE_WEAPON_TO_CHAR(peds3[i], (uint)eWeaponType.WEAPON_KNIFE, 1, false);
                                }
                            }
                        }
                    }
                }
            }
         
            //PedPools 
            int[] peds4 = CPools.GetAllPedHandles();
            for (int i = 0; i < peds4.Length; i++)
            {
                if (DOES_CHAR_EXIST(peds3[i]))
                {
                    m3 = 0;
                    GET_CHAR_MODEL(peds3[i], out m3);
                    {
                        if (((m3 != 0) && (findmodel4 == 0)) || (findmodel4 == m3))
                        {
                            if (!IS_CHAR_DEAD(peds4[i]))
                            {

                                if (IS_CHAR_IN_ANY_HELI(peds4[i]) && !HAS_CHAR_GOT_WEAPON(peds4[i], (int)eWeaponType.WEAPON_KNIFE))
                                {
                                    REMOVE_ALL_CHAR_WEAPONS(peds4[i]);
                                    GIVE_WEAPON_TO_CHAR(peds4[i], (uint)eWeaponType.WEAPON_EPISODIC_14, 1000, false);
                                    GIVE_WEAPON_TO_CHAR(peds4[i], (uint)eWeaponType.WEAPON_KNIFE, 1, false);
                                }
                            }
                        }
                    }
                }
            }

            #endregion


           
        }
        
        //todo
        private void Main_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.NumPad7)
            {
                uint model2;
                int
                    polpat = (int)eVehicle.VEHICLE_POLPATRIOT;

                #region PoliceVehicles

                int[] vehs = CPools.GetAllVehicleHandles();

                for (int i = 0; i < vehs.Length; i++)
                {
                    if (DOES_VEHICLE_EXIST(vehs[i]))
                    {
                        model2 = 0;
                        GET_CAR_MODEL(vehs[i], out model2);
                        {
                            if (((model2 != 0) && (polpat == 0)) || (polpat == model2))
                            {
                                if (!IS_CAR_DEAD(vehs[i]))
                                {
                                    int driver;
                                    GET_DRIVER_OF_CAR(vehs[i], out driver);
                                    if (driver == (int)ePed.PED_M_Y_COP)
                                    {
                                        SET_CAR_LIVERY(vehs[i], 2);
                                    }

                                    //more
                                }
                            }
                        }
                    }
                }
                #endregion
                
            }
        }
    }
}
```
contract me in Discord: Kensukeken#2016
