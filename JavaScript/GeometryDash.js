 //Create Death count


var currentCheckpoint = 0;
var currentGravity = 0;
var currentSize = 0;
var speedMultiplier = 0.9; //3.9
//
var respawnTimer = 1;
//var globalTimer = 0;
var lastDirection = 0;
camera.zoom = 0.4; //0.4
var maxRespawn = 75;
var debugCollision = 0;

var timeTaken = 0;
var timeEnable = 1;
var deathCount = 0;



var backgroundObj = createSprite(200,-950);
backgroundObj.setAnimation("Background1");
backgroundObj.scale = 4;
backgroundObj.tint = "rgb(100, 150, 235)";

var backgroundObj2 = createSprite(1800,-950);
backgroundObj2.setAnimation("Background1");
backgroundObj2.scale = 4;
backgroundObj2.tint = "rgb(100, 150, 235)";



var checkpoints = createGroup();

var checkpoint_1 = createSprite(4399, -250);
checkpoints.add(checkpoint_1);

var checkpoint_2 = createSprite(11000, -250);
checkpoints.add(checkpoint_2);

var checkpoint_3 = createSprite(15750, -250);
checkpoints.add(checkpoint_3);

var checkpoint_4 = createSprite(21500, -250);
checkpoints.add(checkpoint_4);

var checkpoint_5 = createSprite(30600, -250);
checkpoints.add(checkpoint_5);

checkpoints.setAnimationEach("checkpointInactive");
checkpoints.setScaleEach(1.25);


var respawnCircle = createSprite(200,-200);
respawnCircle.setAnimation("respawnCircle");
respawnCircle.scale = 0.0;
respawnCircle.rotationSpeed = 50;
respawnCircle.tint = "rgb(0, 240, 255)";


var effectCircle = createSprite(200, -200);
effectCircle.setAnimation("respawnCircle");
effectCircle.scale = 0.0;

var effectCircle2 = createSprite(200, -200);
effectCircle2.setAnimation("effectCircleOpen");
effectCircle2.scale = 0.0;


//var jumpRing_1 = createSprite(2600, -400);

var jumpRings = createGroup();
jumpRings.add(createSprite(11750, -350));
jumpRings.add(createSprite(13400, -780));
jumpRings.add(createSprite(15100, -400));

jumpRings.add(createSprite(19600, -650));
jumpRings.add(createSprite(26200, -700));
jumpRings.add(createSprite(26900, -700));
jumpRings.add(createSprite(27200, -600));

jumpRings.add(createSprite(28300, -1000));
jumpRings.add(createSprite(28500, -875));
jumpRings.add(createSprite(28700, -750));

jumpRings.add(createSprite(34600, -400));
jumpRings.add(createSprite(35700, -600));

jumpRings.add(createSprite(37075, -400));
jumpRings.add(createSprite(36925, -550));
jumpRings.add(createSprite(37075, -700));
jumpRings.add(createSprite(36925, -850));
jumpRings.add(createSprite(37075, -1000));
jumpRings.add(createSprite(36925, -1150));
jumpRings.add(createSprite(37075, -1300));


jumpRings.setAnimationEach("jumpRing");
jumpRings.setScaleEach(1.1);


//var jumpPad_1 = createSprite(2400, -160);

var jumpPads = createGroup();
jumpPads.add(createSprite(5000, -160));
jumpPads.add(createSprite(8600, -460));
jumpPads.add(createSprite(9900, -160));
jumpPads.add(createSprite(10400, -160));

jumpPads.add(createSprite(12900, -160));

jumpPads.add(createSprite(23700, -160));
jumpPads.add(createSprite(24900, -560));
var jumpPad1 = createSprite(25900, -840);
jumpPads.add(jumpPad1);
jumpPad1.rotation = 180;

jumpPads.add(createSprite(33400, -160));

jumpPads.add(createSprite(37000, -160));
jumpPads.setAnimationEach("jumpPad");


//var speed0_1 = createSprite(3200, -300);
var speed0 = createGroup();
speed0.add(createSprite(39800, -300));
speed0.setAnimationEach("slowSpeed");
speed0.setScaleEach(0.8);


//var speed1_1 = createSprite(4300, -300);
var speed1 = createGroup();
speed1.add(createSprite(42000, -300));
speed1.setAnimationEach("normalSpeed");
speed1.setScaleEach(0.8);

//2x SPEED
//var speed2_1 = createSprite(3800, -300);
var speed2 = createGroup();
speed2.add(createSprite(31300, -400));
speed2.setAnimationEach("fastSpeed");
speed2.setScaleEach(0.8);

//3x SPEED
//var speed3_1 = createSprite(4100, -300);
var speed3 = createGroup();
speed3.add(createSprite(38100, -600));
speed3.setAnimationEach("fasterSpeed");
speed3.setScaleEach(0.8);


var player = createSprite(-10,-200);
player.setAnimation("Cube_idle");




var miniPortals = createGroup();

miniPortals.add(createSprite(22000, -300));

miniPortals.add(createSprite(36000, -800));

miniPortals.setAnimationEach("miniPortal");
miniPortals.setScaleEach(2);



var bigPortals = createGroup();
var bigPortal1 = createSprite(28550, -375);
bigPortals.add(bigPortal1);
bigPortal1.rotation = 180;
bigPortal1.scale = 1.1;

bigPortals.add(createSprite(38100, -600));

bigPortals.setAnimationEach("bigPortal");
bigPortals.setScaleEach(2.2);


var bluePortals = createGroup();


var bluePortal1 = createSprite(20450, -400);
bluePortals.add(bluePortal1);

var bluePortal2 = createSprite(27400, -800);
bluePortals.add(bluePortal2);
bluePortal2.rotation = -45;

var bluePortal3 = createSprite(27700, -500);
bluePortals.add(bluePortal3);
bluePortal3.rotation = -45;

bluePortals.add(createSprite(29000, -600));

bluePortals.add(createSprite(38600, -500));

bluePortals.setAnimationEach("bluePortal");
bluePortals.setScaleEach(0.8);

var yellowPortals = createGroup();

var yellowPortal1 = createSprite(17500, -400);
yellowPortals.add(yellowPortal1);
yellowPortal1.rotation = 90;

var yellowPortal2 = createSprite(25300, -575);
yellowPortals.add(yellowPortal2);
yellowPortal2.rotation = 90;
yellowPortal2.scale = 0.9;

var yellowPortal3 = createSprite(37600, -700);
yellowPortals.add(yellowPortal3);
yellowPortal3.rotation = 90;

yellowPortals.setAnimationEach("yellowPortal");
yellowPortals.setScaleEach(0.8);


/////////////////////////////////////////////////////////////////////////

// BLOCK LIST
// GROUPS
var block = createGroup();
var blockCollision = createGroup();

// Creating Sprites
var block1 = createSprite(2900,-200);
block1.setAnimation("1x5");
block.add(block1);
block1.rotation = 90;
var block1C = createSprite(2900,-192);
block1C.scale = 1.02;
block1C.setAnimation("1x5_collision");
blockCollision.add(block1C);
block1C.rotation = 90;

var block2 = createSprite(3800, -600);
block2.setAnimation("1x2");
block.add(block2);
var block2C = createSprite(3800,-597);
block2C.scale = 1.02;
block2C.setAnimation("1x2_collision");
block2C.width = 120;
blockCollision.add(block2C);

var block3 = createSprite(3800,-100);
block3.setAnimation("1x5");
block.add(block3);
var block3C = createSprite(3800,-95);
block3C.scale = 1.02;
block3C.setAnimation("1x5_collision");
blockCollision.add(block3C);

var block4 = createSprite(2850, -800);
block4.setAnimation("1x2");
block.add(block4);
block4.rotation = 90;
var block4C = createSprite(2850,-799);
block4C.scale = 1.05;
block4C.setAnimation("1x2_collision");
block4C.width = 120;
blockCollision.add(block4C);
block4C.rotation = 90;

var block5 = createSprite(6800, 0);
block5.setAnimation("5x5");
block.add(block5);
var block5C = createSprite(6800, 12);
block5C.scale = 1.02;
block5C.setAnimation("5x5_collision");
blockCollision.add(block5C);

var block6 = createSprite(7500, -100);
block6.setAnimation("5x5");
block.add(block6);
var block6C = createSprite(7500, -88);
block6C.scale = 1.02;
block6C.setAnimation("5x5_collision");
blockCollision.add(block6C);

var block7 = createSprite(8000, -445);
block7.setAnimation("platform_1");
block.add(block7);
var block7C = createSprite(8000, -440);
block7C.scale = 1.02;
block7C.setAnimation("platform_1_collision");
blockCollision.add(block7C);

var block8 = createSprite(8300, -545);
block8.setAnimation("platform_1");
block.add(block8);
var block8C = createSprite(8300, -540);
block8C.scale = 1.02;
block8C.setAnimation("platform_1_collision");
blockCollision.add(block8C);

var block9 = createSprite(8600, -200);
block9.setAnimation("1x5");
block.add(block9);
var block9C = createSprite(8600, -195);
block9C.scale = 1.02;
block9C.setAnimation("1x5_collision");
blockCollision.add(block9C);

var block10 = createSprite(6800, -800);
block10.setAnimation("1x2");
block.add(block10);
block10.rotation = 90;
var block10C = createSprite(6800,-799);
block10C.scale = 1.05;
block10C.setAnimation("1x2_collision");
block10C.width = 120;
blockCollision.add(block10C);
block10C.rotation = 90;

var block11 = createSprite(5950, -700);
block11.setAnimation("1x2");
block.add(block11);
block11.rotation = 90;
var block11C = createSprite(5950,-699);
block11C.scale = 1.05;
block11C.setAnimation("1x2_collision");
block11C.width = 120;
blockCollision.add(block11C);
block11C.rotation = 90;

var block12 = createSprite(7500, -900);
block12.setAnimation("1x2");
block.add(block12);
block12.rotation = 90;
var block12C = createSprite(7500,-899);
block12C.scale = 1.05;
block12C.setAnimation("1x2_collision");
block12C.width = 120;
blockCollision.add(block12C);
block12C.rotation = 90;

var block13 = createSprite(9900,-650);
block13.setAnimation("1x5");
block.add(block13);
block13.rotation = 90;
var block13C = createSprite(9900,-650);
block13C.scale = 1.02;
block13C.setAnimation("1x5_collision");
blockCollision.add(block13C);
block13C.rotation = 90;

var block14 = createSprite(10400,-650);
block14.setAnimation("1x5");
block.add(block14);
block14.rotation = 90;
var block14C = createSprite(10400,-650);
block14C.scale = 1.02;
block14C.setAnimation("1x5_collision");
blockCollision.add(block14C);
block14C.rotation = 90;

var block15 = createSprite(8300, -545);
block15.setAnimation("platform_1");
block.add(block15);
var block15C = createSprite(8300, -540);
block15C.scale = 1.02;
block15C.setAnimation("platform_1_collision");
blockCollision.add(block15C);

var block16 = createSprite(13200,-270);
block16.setAnimation("1x5");
block.add(block16);
var block16C = createSprite(13200,-250);
block16C.scale = 1.05;
block16C.setAnimation("1x5_collision");
blockCollision.add(block16C);

var block17 = createSprite(13550,-314);
block17.setAnimation("2x5");
block.add(block17);
var block17C = createSprite(13550,-300);
block17C.scale = 1.05;
block17C.setAnimation("2x5_collision");
blockCollision.add(block17C);

var block18 = createSprite(14000,-400);
block18.setAnimation("1x5");
block.add(block18);
block18.rotation = 90;
var block18C = createSprite(14000,-380);
block18C.scale = 1.02;
block18C.setAnimation("1x5_collision");
blockCollision.add(block18C);
block18C.rotation = 90;

var block19 = createSprite(14400,-270);
block19.setAnimation("1x5");
block.add(block19);
var block19C = createSprite(14400,-250);
block19C.scale = 1.05;
block19C.setAnimation("1x5_collision");
blockCollision.add(block19C);

var block20 = createSprite(15100,-675);
block20.setAnimation("1x5");
block.add(block20);
block20.rotation = 90;
var block20C = createSprite(15100,-675);
block20C.scale = 1.04;
block20C.setAnimation("1x5_collision");
blockCollision.add(block20C);
block20C.rotation = 90;

var block21 = createSprite(16500, -100);
block21.setAnimation("1x5");
block.add(block21);
var block21C = createSprite(16500, -70);
block21C.scale = 1.1;
block21C.setAnimation("1x5_collision");
blockCollision.add(block21C);

var block22 = createSprite(17000, -200);
block22.setAnimation("5x5");
block.add(block22);
var block22C = createSprite(17000, -167);
block22C.scale = 1.06;
block22C.setAnimation("5x5_collision");
blockCollision.add(block22C);

var block23 = createSprite(16950, -900);
block23.setAnimation("1x2");
block.add(block23);
block23.rotation = 90;
var block23C = createSprite(16950,-899);
block23C.scale = 1.05;
block23C.setAnimation("1x2_collision");
block23C.width = 120;
blockCollision.add(block23C);
block23C.rotation = 90;

var block24 = createSprite(17900, -800);
block24.setAnimation("1x5");
block.add(block24);
block24.rotation = 90;
var block24C = createSprite(17900, -815);
block24C.scale = 1.1;
block24C.setAnimation("1x5_collision");
blockCollision.add(block24C);
block24C.rotation = 90;

var block25 = createSprite(18400, -800);
block25.setAnimation("1x5");
block.add(block25);
block25.rotation = 90;
var block25C = createSprite(18400, -815);
block25C.scale = 1.1;
block25C.setAnimation("1x5_collision");
blockCollision.add(block25C);
block25C.rotation = 90;

var block26 = createSprite(19100, -700);
block26.setAnimation("1x5");
block.add(block26);
block26.rotation = 90;
var block26C = createSprite(19100, -715);
block26C.scale = 1.1;
block26C.setAnimation("1x5_collision");
blockCollision.add(block26C);
block26C.rotation = 90;

var block27 = createSprite(20100, -700);
block27.setAnimation("1x5");
block.add(block27);
block27.rotation = 90;
var block27C = createSprite(20100, -715);
block27C.scale = 1.1;
block27C.setAnimation("1x5_collision");
blockCollision.add(block27C);
block27C.rotation = 90;

var block28 = createSprite(22850, -475);
block28.setAnimation("platform_1");
block.add(block28);
var block28C = createSprite(22850, -470);
block28C.scale = 1.02;
block28C.setAnimation("platform_1_collision");
blockCollision.add(block28C);

var block29 = createSprite(23350, -475);
block29.setAnimation("platform_1");
block.add(block29);
var block29C = createSprite(22350, -470);
block29C.scale = 1.02;
block29C.setAnimation("platform_1_collision");
blockCollision.add(block29C);

var block30 = createSprite(23450, -475);
block30.setAnimation("platform_1");
block.add(block30);
var block30C = createSprite(23450, -475);
block30C.scale = 1.05;
block30C.setAnimation("platform_1_collision");
blockCollision.add(block30C);

var block31 = createSprite(24600, -200);
block31.setAnimation("1x5");
block.add(block31);
var block31C = createSprite(24600, -161);
block31C.scale = 1.15;
block31C.setAnimation("1x5_collision");
blockCollision.add(block31C);

var block32 = createSprite(24100, -100);
block32.setAnimation("5x5");
block.add(block32);
var block32C = createSprite(24100, -82);
block32C.scale = 1.04;
block32C.setAnimation("5x5_collision");
blockCollision.add(block32C);

var block33 = createSprite(24900, -300);
block33.setAnimation("1x5");
block.add(block33);
var block33C = createSprite(24900, -261);
block33C.scale = 1.15;
block33C.setAnimation("1x5_collision");
blockCollision.add(block33C);

var block34 = createSprite(25700, -1100);
block34.setAnimation("5x5");
block.add(block34);
var block34C = createSprite(25700, -1118);
block34C.scale = 1.04;
block34C.setAnimation("5x5_collision");
blockCollision.add(block34C);

var block35 = createSprite(25700, -100);
block35.setAnimation("5x5");
block.add(block35);
var block35C = createSprite(25700, -118);
block35C.scale = 1.04;
block35C.setAnimation("5x5_collision");
blockCollision.add(block35C);

var block36 = createSprite(26600,-1000);
block36.setAnimation("2x5");
block.add(block36);
var block36C = createSprite(26600,-1014);
block36C.scale = 1.05;
block36C.setAnimation("2x5_collision");
blockCollision.add(block36C);

var block37 = createSprite(27800, -1300);
block37.setAnimation("5x5");
block.add(block37);
var block37C = createSprite(27800, -1318);
block37C.scale = 1.04;
block37C.setAnimation("5x5_collision");
blockCollision.add(block37C);

var block38 = createSprite(31300,-200);
block38.setAnimation("1x5");
block.add(block38);
block38.rotation = 90;
var block38C = createSprite(31300,-190);
block38C.scale = 1.05;
block38C.setAnimation("1x5_collision");
blockCollision.add(block38C);
block38C.rotation = 90;

var block39 = createSprite(33400, -900);
block39.setAnimation("5x5");
block.add(block39);
var block39C = createSprite(33400, -882);
block39C.scale = 1.04;
block39C.setAnimation("5x5_collision");
blockCollision.add(block39C);

var block40 = createSprite(34000,-1000);
block40.setAnimation("2x5");
block.add(block40);
var block40C = createSprite(34000,-1014);
block40C.scale = 1.05;
block40C.setAnimation("2x5_collision");
blockCollision.add(block40C);

var block41 = createSprite(35200, -300);
block41.setAnimation("5x5");
block.add(block41);
var block41C = createSprite(35200, -282);
block41C.scale = 1.05;
block41C.setAnimation("5x5_collision");
blockCollision.add(block41C);

var block42 = createSprite(35600, -1100);
block42.setAnimation("1x2");
block.add(block42);
block42.rotation = 90;
var block42C = createSprite(35600,-1099);
block42C.scale = 1.05;
block42C.setAnimation("1x2_collision");
block42C.width = 120;
blockCollision.add(block42C);
block42C.rotation = 90;

var block43 = createSprite(36000,-300);
block43.setAnimation("1x5");
block.add(block43);
var block43C = createSprite(36000,-290);
block43C.scale = 1.05;
block43C.setAnimation("1x5_collision");
blockCollision.add(block43C);

var block44 = createSprite(37300,-1000);
block44.setAnimation("1x5");
block.add(block44);
var block44C = createSprite(37300,-990);
block44C.scale = 1.05;
block44C.setAnimation("1x5_collision");
blockCollision.add(block44C);

var block45 = createSprite(37300,-500);
block45.setAnimation("1x5");
block.add(block45);
var block45C = createSprite(37300,-490);
block45C.scale = 1.05;
block45C.setAnimation("1x5_collision");
blockCollision.add(block45C);

var block46 = createSprite(37300, 0);
block46.setAnimation("1x5");
block.add(block46);
var block46C = createSprite(37300, 10);
block46C.scale = 1.05;
block46C.setAnimation("1x5_collision");
blockCollision.add(block46C);

var block47 = createSprite(38100, -800);
block47.setAnimation("1x5");
block.add(block47);
block47.rotation = 90;
var block47C = createSprite(38100, -810);
block47C.scale = 1.05;
block47C.setAnimation("1x5_collision");
blockCollision.add(block47C);
block47C.rotation = 90;

// Unique Block Attributes
blockCollision.setVisibleEach(false);


// HAZARDS ///////////////////////////////////////////////////////////////

// Groups
var spikes = createGroup();
//var spikes_2 = createGroup();

// Creating Sprites
var spike1 = createSprite(1200,-200);
spikes.add(spike1);
var spike2 = createSprite(2000,-200);
spikes.add(spike2);
var spike3 = createSprite(3100, -300);
spikes.add(spike3);
var spike4 = createSprite(3885, -585);
spikes.add(spike4);
spike4.scale = 0.75;
spike4.rotation = 90;
var spike5 = createSprite(3715, -585);
spikes.add(spike5);
spike5.scale = 0.75;
spike5.rotation = 270;
var spike6 = createSprite(3710, -180);
spikes.add(spike6);
spike6.scale = 0.5;
var spike7 = createSprite(3890, -180);
spikes.add(spike7);
spike7.scale = 0.5;
var spike8 = createSprite(2800, -700);
spikes.add(spike8);
spike8.rotation = 180;
var spike9 = createSprite(2900, -700);
spikes.add(spike9);
spike9.rotation = 180;

var spike10 = createSprite(5100, -200);
spikes.add(spike10);
var spike11 = createSprite(5190, -200);
spikes.add(spike11);
var spike12 = createSprite(5280, -200);
spikes.add(spike12);
var spike13 = createSprite(5370, -200);
spikes.add(spike13);
var spike14 = createSprite(5900, -200);
spikes.add(spike14);
var spike15 = createSprite(5990, -200);
spikes.add(spike15);

var spike16 = createSprite(6500, -200);
spikes.add(spike16);
var spike17 = createSprite(7100, -190);
spikes.add(spike17);
spike17.scale = 0.9;
var spike18 = createSprite(7200, -190);
spikes.add(spike18);
spike18.scale = 0.9;
var spike19 = createSprite(7800, -185);
spikes.add(spike19);
spike19.scale = 0.75;
var spike20 = createSprite(7900, -185);
spikes.add(spike20);
spike20.scale = 0.75;
var spike21 = createSprite(8000, -185);
spikes.add(spike21);
spike21.scale = 0.75;
var spike22 = createSprite(8100, -185);
spikes.add(spike22);
spike22.scale = 0.75;
var spike23 = createSprite(8200, -185);
spikes.add(spike23);
spike23.scale = 0.75;
var spike24 = createSprite(8300, -185);
spikes.add(spike24);
spike24.scale = 0.75;
var spike25 = createSprite(8400, -185);
spikes.add(spike25);
spike25.scale = 0.75;
var spike26 = createSprite(8500, -185);
spikes.add(spike26);
spike26.scale = 0.75;
var spike27 = createSprite(8700, -200);
spikes.add(spike27);
var spike28 = createSprite(8800, -200);
spikes.add(spike28);
var spike29 = createSprite(8900, -200);
spikes.add(spike29);
var spike30 = createSprite(9000, -200);
spikes.add(spike30);
var spike31 = createSprite(9080, -180);
spikes.add(spike31);
spike31.scale = 0.8;

var spike32 = createSprite(6000, -600);
spikes.add(spike32);
spike32.rotation = 180;
var spike33 = createSprite(5900, -600);
spikes.add(spike33);
spike33.rotation = 180;

var spike34 = createSprite(6750, -700);
spikes.add(spike34);
spike34.rotation = 180;
var spike35 = createSprite(6850, -700);
spikes.add(spike35);
spike35.rotation = 180;
var spike36 = createSprite(7550, -800);
spikes.add(spike36);
spike36.rotation = 180;
var spike37 = createSprite(7450, -800);
spikes.add(spike37);
spike37.rotation = 180;
var spike38 = createSprite(10100, -550);
spikes.add(spike38);
spike38.rotation = 180;
var spike39 = createSprite(10000, -550);
spikes.add(spike39);
spike39.rotation = 180;
var spike40 = createSprite(9900, -550);
spikes.add(spike40);
spike40.rotation = 180;
var spike41 = createSprite(9800, -550);
spikes.add(spike41);
spike41.rotation = 180;
var spike42 = createSprite(9700, -550);
spikes.add(spike42);
spike42.rotation = 180;
var spike43 = createSprite(10200, -550);
spikes.add(spike43);
spike43.rotation = 180;
var spike44 = createSprite(10300, -550);
spikes.add(spike44);
spike44.rotation = 180;
var spike45 = createSprite(10400, -550);
spikes.add(spike45);
spike45.rotation = 180;
var spike46 = createSprite(10500, -550);
spikes.add(spike46);
spike46.rotation = 180;
var spike47 = createSprite(10600, -550);
spikes.add(spike47);
spike47.rotation = 180;

var spike48 = createSprite(11600, -190);
spikes.add(spike48);
spike48.scale = 0.75;
var spike49 = createSprite(11700, -200);
spikes.add(spike49);
var spike50 = createSprite(11800, -200);
spikes.add(spike50);
var spike51 = createSprite(11900, -190);
spikes.add(spike51);
spike51.scale = 0.75;
var spike52 = createSprite(12400, -200);
spikes.add(spike52);
var spike53 = createSprite(12500, -190);
spikes.add(spike53);
spike53.scale = 0.75;
var spike54 = createSprite(13400, -200);
spikes.add(spike54);
var spike55 = createSprite(13300, -200);
spikes.add(spike55);
var spike56 = createSprite(13100, -200);
spikes.add(spike56);
var spike57 = createSprite(13000, -190);
spikes.add(spike57);
spike57.scale = 0.75;

var spike58 = createSprite(13600, -615);
spikes.add(spike58);
var spike59 = createSprite(13500, -615);
spikes.add(spike59);
spikes.add(createSprite(13700, -180));
spikes.add(createSprite(13800, -200));
spikes.add(createSprite(13900, -180));
spikes.add(createSprite(14000, -200));
spikes.add(createSprite(14100, -180));
spikes.add(createSprite(14200, -200));
spikes.add(createSprite(14300, -180));
spikes.add(createSprite(14400, -570));

spikes.add(createSprite(15020, -200));
spikes.add(createSprite(15180, -200));
spikes.add(createSprite(15100, -200));

var spike60 = createSprite(15000, -585);
spikes.add(spike60);
spike60.rotation = 180;
spike60.scale = 0.85;
var spike61 = createSprite(14900, -585);
spikes.add(spike61);
spike61.rotation = 180;
spike61.scale = 0.85;
var spike62 = createSprite(15100, -585);
spikes.add(spike62);
spike62.rotation = 180;
spike62.scale = 0.85;
var spike63 = createSprite(15200, -585);
spikes.add(spike63);
spike63.rotation = 180;
spike63.scale = 0.85;
var spike64 = createSprite(15300, -585);
spikes.add(spike64);
spike64.rotation = 180;
spike64.scale = 0.85;

spikes.add(createSprite(16400, -200));
spikes.add(createSprite(16600, -200));
spikes.add(createSprite(16700, -200));
spikes.add(createSprite(17200, -500));

var spike65 = createSprite(17000, -800);
spikes.add(spike65);
spike65.rotation = 180;
var spike66 = createSprite(16900, -800);
spikes.add(spike66);
spike66.rotation = 180;
var spike67 = createSprite(18100, -700);
spikes.add(spike67);
spike67.rotation = 180;
var spike68 = createSprite(18200, -700);
spikes.add(spike68);
spike68.rotation = 180;
var spike69 = createSprite(20300, -600);
spikes.add(spike69);
spike69.rotation = 180;

spikes.add(createSprite(17300, -200));
spikes.add(createSprite(17400, -200));
spikes.add(createSprite(17500, -200));
spikes.add(createSprite(17600, -200));
spikes.add(createSprite(17700, -175));

spikes.add(createSprite(20300, -175));
spikes.add(createSprite(20200, -200));
spikes.add(createSprite(20100, -200));
spikes.add(createSprite(20000, -200));
spikes.add(createSprite(19900, -175));

spikes.add(createSprite(20815, -200));
spikes.add(createSprite(20900, -200));
spikes.add(createSprite(20985, -200));

spikes.add(createSprite(22400, -175));
spikes.add(createSprite(22850, -200));
var spike70 = createSprite(22850, -400);
spikes.add(spike70);
spike70.rotation = 180;
var spike71 = createSprite(23350, -400);
spikes.add(spike71);
spike71.rotation = 180;
var spike72 = createSprite(23450, -400);
spikes.add(spike72);
spike72.rotation = 180;

spikes.add(createSprite(23350, -200));
spikes.add(createSprite(23450, -150));
spikes.add(createSprite(23800, -200));

var spike73 = createSprite(24500, -400);
spikes.add(spike73);
spike73.rotation = 270;
var spike74 = createSprite(24400, -350);
spikes.add(spike74);
spike74.rotation = 270;
var spike75 = createSprite(24400, -300);
spikes.add(spike75);
spike75.rotation = 90;
var spike76 = createSprite(24500, -350);
spikes.add(spike76);
spike76.rotation = 90;

var spike77 = createSprite(24800, -500);
spikes.add(spike77);
spike77.rotation = 270;
var spike78 = createSprite(24800, -450);
spikes.add(spike78);
spike78.rotation = 90;
var spike79 = createSprite(24700, -400);
spikes.add(spike79);
spike79.rotation = 90;
var spike80 = createSprite(24700, -450);
spikes.add(spike80);
spike80.rotation = 270;
var spike81 = createSprite(25500, -800);
spikes.add(spike81);
spike81.rotation = 180;
var spike82 = createSprite(25600, -800);
spikes.add(spike82);
spike82.rotation = 180;

spikes.add(createSprite(25000, -200));
spikes.add(createSprite(25100, -200));
spikes.add(createSprite(25200, -200));
spikes.add(createSprite(25300, -200));
spikes.add(createSprite(25400, -200));

spikes.add(createSprite(26900, -175));
spikes.add(createSprite(27000, -200));
spikes.add(createSprite(27100, -175));
spikes.add(createSprite(27200, -200));
spikes.add(createSprite(27300, -175));
spikes.add(createSprite(27400, -200));
spikes.add(createSprite(27500, -175));
spikes.add(createSprite(27600, -200));
spikes.add(createSprite(27700, -175));
spikes.add(createSprite(27800, -200));
spikes.add(createSprite(27900, -175));
spikes.add(createSprite(28000, -200));
spikes.add(createSprite(28100, -175));
spikes.add(createSprite(28200, -200));
spikes.add(createSprite(28300, -175));
spikes.add(createSprite(28400, -200));
spikes.add(createSprite(28500, -175));

spikes.add(createSprite(29410, -185));
spikes.add(createSprite(29500, -200));
spikes.add(createSprite(29590, -185));

spikes.add(createSprite(29910, -185));
spikes.add(createSprite(30000, -200));
spikes.add(createSprite(30090, -185));

spikes.add(createSprite(32000, -200));
spikes.add(createSprite(32100, -200));
spikes.add(createSprite(32200, -200));

spikes.add(createSprite(32700, -200));
spikes.add(createSprite(32780, -200));
spikes.add(createSprite(32860, -200));
spikes.add(createSprite(32940, -200));

spikes.add(createSprite(31500, -300));
spikes.add(createSprite(31000, -200));

var spike83 = createSprite(33200, -600);
spikes.add(spike83);
spike83.rotation = 180;
var spike84 = createSprite(33300, -600);
spikes.add(spike84);
spike84.rotation = 180;
var spike85 = createSprite(33400, -600);
spikes.add(spike85);
spike85.rotation = 180;
var spike86 = createSprite(33500, -600);
spikes.add(spike86);
spike86.rotation = 180;
var spike87 = createSprite(33600, -600);
spikes.add(spike87);
spike87.rotation = 180;
var spike88 = createSprite(34910, -400);
spikes.add(spike88);
spike88.rotation = 270;
spike88.scale = 0.9;

spikes.add(createSprite(33950, -200));
spikes.add(createSprite(34050, -200));

spikes.add(createSprite(34400, -185));
spikes.add(createSprite(34500, -200));
spikes.add(createSprite(34600, -200));
spikes.add(createSprite(34700, -200));
spikes.add(createSprite(34800, -200));
spikes.add(createSprite(34900, -185));

spikes.add(createSprite(35100, -600));
spikes.add(createSprite(35200, -600));
spikes.add(createSprite(35300, -600));

var spike89 = createSprite(35490, -400);
spikes.add(spike89);
spike89.rotation = 90;
spike89.scale = 0.9;

var spike90 = createSprite(35550, -1000);
spikes.add(spike90);
spike90.rotation = 180;

var spike90 = createSprite(35650, -1000);
spikes.add(spike90);
spike90.rotation = 180;

spikes.add(createSprite(35500, -180));
spikes.add(createSprite(35600, -200));
spikes.add(createSprite(35700, -200));
spikes.add(createSprite(35800, -200));
spikes.add(createSprite(35900, -180));

spikes.add(createSprite(36000, -600));

spikes.add(createSprite(36400, -180));
spikes.add(createSprite(36480, -180));
spikes.add(createSprite(36560, -180));

spikes.add(createSprite(37300, -1300));
var spike91 = createSprite(37300, -1500);
spikes.add(spike91);
spike91.rotation = 180;
spikes.add(createSprite(37300, -1600));

spikes.add(createSprite(37900, -900));
spikes.add(createSprite(38000, -900));
spikes.add(createSprite(38100, -900));
spikes.add(createSprite(38200, -900));
spikes.add(createSprite(38300, -900));

spikes.add(createSprite(37500, -200));
spikes.add(createSprite(37700, -200));
spikes.add(createSprite(37900, -200));
spikes.add(createSprite(38100, -200));
spikes.add(createSprite(38300, -200));
spikes.add(createSprite(38500, -200));

spikes.add(createSprite(39000, -200));
spikes.add(createSprite(39080, -200));
spikes.add(createSprite(39160, -200));
spikes.add(createSprite(39240, -200));
spikes.add(createSprite(39320, -200));

spikes.add(createSprite(40300, -200));

spikes.add(createSprite(40700, -200));
spikes.add(createSprite(40790, -180));

spikes.add(createSprite(41490, -200));
spikes.add(createSprite(41400, -180));

var spike92 = createSprite(42300, -200);
spikes.add(spike92);
var spike93 = createSprite(42390, -200);
spikes.add(spike93);
var spike94 = createSprite(42480, -200);
spikes.add(spike94);

// Group Attributes
spikes.setAnimationEach("Spike1");
spikes.setColliderEach("circle", 0, 20, 35);

// FINISH LINE ////////////////////////////////////////////////////
var finishLine = createSprite(43100, -500);
finishLine.setAnimation("finishLine");
finishLine.scale = 1.1;

// GROUND ///////////////////////////////////////////////////////////////
var groundObj = createSprite(175,0);
groundObj.setAnimation("Ground1");
groundObj.scale = 3;
groundObj.tint = "rgb(60, 120, 215)";

var groundObj2 = createSprite(1375,0);
groundObj2.setAnimation("Ground1");
groundObj2.scale = 3;
groundObj2.tint = "rgb(60, 120, 215)";

var groundLine = createSprite(0,-150);
groundLine.setAnimation("groundLine");
groundLine.scale = 3;



/////////////////////////////////////////////////////////////////////////
//MISC
/////////////////////////////////////////////////////////////////////////

var arrowKeysMove = createSprite(0,-700);
arrowKeysMove.setAnimation("arrowKeysMove");
var arrowKeysJump = createSprite(1200,-700);
arrowKeysJump.setAnimation("arrowKeysJump");




/////////////////////////////////////////////////////////////////////////
// DEBUG
player.debug = false;
groundObj.debug = false;
groundObj2.debug = false;
backgroundObj.debug = false;
backgroundObj2.debug = false;


// DRAW /////////////////////////////////////////////////////////////////
function draw() {
  // draw background
background("white");

  // update sprites

BG();
infiniteGround();
infiniteBackground();

playerSizePortals();
playerSpeedPortals();
effectCircleFunc();
playerGravityPortals();

playerMovement();
playerObjects();


playerCollision();
playerGravity();
playerBounds();

playerCheckpoints();
cameraMovement();
playerRespawn();

finishGame();
drawSprites();



  // other

}
///////////////////////////////////////////////////////////////////////


// Create your functions here
function playerMovement() {
  // Velocity
  if (player.velocityX > 20 * speedMultiplier) {
    player.velocityX = 20 * speedMultiplier;
  }
  if (player.velocityX < -15 * speedMultiplier) {
    player.velocityX = -15 * speedMultiplier;
  }

  // Actually moving
  if (keyDown("right") && respawnTimer > maxRespawn) {
  player.velocityX = player.velocityX + 5.0*speedMultiplier;
  lastDirection = 0;
  if (currentGravity == 0) {
  player.setAnimation("Cube_right");
    } else {
  player.setAnimation("U_Cube_right");
    }
  }
  if (keyDown("left") && respawnTimer > maxRespawn) {
  player.velocityX = player.velocityX - 5.0*speedMultiplier;
  lastDirection = 1;
  if (currentGravity == 0) {
  player.setAnimation("Cube_left");
    } else {
  player.setAnimation("U_Cube_left");
    }
  }
  
  // Slowing down
  if (!keyDown("right") && player.velocityX > 0) {
    player.velocityX = player.velocityX - 5.0*speedMultiplier;
  }
  if (!keyDown("left") && player.velocityX < 0) {
    player.velocityX = player.velocityX + 5.0*speedMultiplier;
  }
  if (!keyDown("right") && player.velocityX > 0.00 && player.velocityX < 5.00) {
    player.velocityX = 0;
  }
  if (!keyDown("left") && player.velocityX < 0.00 && player.velocityX > -5.00) {
    player.velocityX = 0;
  }
  // Stopping
  if (!keyDown("right") && !keyDown("left")) {
    if (currentGravity == 0) {
    player.setAnimation("Cube_idle");
    } else {
    player.setAnimation("U_Cube_idle");
    }
  }
  
  // Jumping
  if (currentGravity == 0) {
  if (player.collide(groundLine) || player.collide(block)) {
    
     if (keyDown("up_arrow")) {
        if (respawnTimer > maxRespawn) {
          if (currentSize == 0) {
       player.velocityY = -50; //-50
          } else {
       player.velocityY = -40;
            }
          }
        }
    if (keyDown("space")) {
        if (respawnTimer > maxRespawn) {
          if (currentSize == 0) {
           player.velocityY = -50; //-50
          } else {
           player.velocityY = -40;
            }
          }
        }
      
    }
  }
      if (currentGravity == 1) {
  if (player.collide(block)) {

     if (keyDown("up_arrow")) {
        if (respawnTimer > maxRespawn) {
          if (currentSize == 0) {
       player.velocityY = 50; //-50
          } else {
       player.velocityY = 40;
            }
          }
        }
      
    }
  }
}

// something i should do faster
function finishGame() {
  if (player.isTouching(finishLine)) {
    player.velocityX = 0;
    player.velocityY = 0;
    timeEnable = 0;
    backgroundObj.y = -10000;
    backgroundObj2.y = -10000;
    groundObj.y = -10000;
    groundObj2.y = -10000;
    groundLine.y = -10000;
    spike92.y = -10000;
    spike93.y = -10000;
    spike94.y = -10000;
    
    textFont("Impact");
    stroke("black");
    fill("black");
    textAlign("CENTER");
    textSize(90);
    text("Level Complete!", camera.x - 400, camera.y - 200);
    textSize(70);
    text("Thanks for playing.", camera.x - 400, camera.y - 100);
    textSize(50);
    text("DEATHS:  " + deathCount, camera.x - 400, camera.y + 100);
    text("TIME TAKEN:  " + timeTaken + " SECONDS", camera.x - 400, camera.y + 200);
  }
  
  if (timeEnable == 1) {
    timeTaken = World.seconds;
  }
}

function playerCollision() {
  groundLine.x = camera.x;
  player.collide(groundLine);
  player.collide(blockCollision);
  player.collide(block);
}

function playerObjects() {
  // JUMP RINGS
  
    if (jumpRings.isTouching(player) && keyWentDown("up")) {
      
      if (currentSize == 0) {
    effectCircle2.tint = "rgb(245, 255, 20)";
    effectCircle2.scale = 2;
    if (currentGravity == 0) {
    player.velocityY = -50;
      } else {
    player.velocityY = 50;
      }
    } else {
    effectCircle2.tint = "rgb(245, 255, 20)";
    effectCircle2.scale = 1.5;
    if (currentGravity == 0) {
    player.velocityY = -40;
      } else {
    player.velocityY = 40;
        }
      }
    }
// -70 and -55

  // JUMP PADS
if (jumpPads.isTouching(player)) {
   if (currentSize == 0) {
    effectCircle2.tint = "rgb(245, 255, 20)";
    effectCircle2.scale = 2;
    if (currentGravity == 0) {
    player.velocityY = -70;
      } else {
    player.velocityY = 70;
      }
    } else {
    effectCircle2.tint = "rgb(245, 255, 20)";
    effectCircle2.scale = 1.5;
    if (currentGravity == 0) {
    player.velocityY = -55;
      } else {
    player.velocityY = 55;
        }
      }
  }
}

function playerGravityPortals() {
  // YELLOW PORTAL
  if (yellowPortals.isTouching(player) && currentGravity == 0) {
   if (player.velocityY > 50) {
     player.velocityY = 50;
   }
    effectCircle.tint = "rgb(255, 255, 0)";
    effectCircle.scale = 2; 
    
    currentGravity = 1;
  }
  // BLUE PORTAL
  if (bluePortals.isTouching(player) && currentGravity == 1) {
   if (player.velocityY < -50) {
     player.velocityY = -50;
   }
    effectCircle.tint = "rgb(0, 255, 255)";
    effectCircle.scale = 2; 

    currentGravity = 0;
  }
  
}

function playerSizePortals() {
  // MINI PORTAL
  if (miniPortals.isTouching(player) && currentSize == 0) {
    
    effectCircle.tint = "rgb(255, 50, 255)";
    effectCircle.scale = 1.75; 
    
    currentSize = 1;
  }
  // BIG PORTAL
   if (bigPortals.isTouching(player) && currentSize == 1) {
   
    effectCircle.tint = "rgb(50, 255, 50)";
    effectCircle.scale = 2.5; 
    
    currentSize = 0;
  }
  if (currentSize == 1) {
    
    player.scale = 0.6;
  } else {
    player.scale = 1;
  }
}

function playerSpeedPortals() {
  // SLOW PORTALS
  if (speed0.isTouching(player)) {
    if (speedMultiplier != 0.9)    {
    effectCircle2.tint = "rgb(255, 180, 50)";
    effectCircle2.scale = 2; 
    }
    speedMultiplier = 0.9;
  }
  
  // 1x SPEED PORTALS
  if (speed1.isTouching(player)) {
    if (speedMultiplier != 1.25)    {
    effectCircle2.tint = "rgb(50, 210, 255)";
    effectCircle2.scale = 2; 
    }
    speedMultiplier = 1.25;
  }
  
  // 2x SPEED PORTALS
  if (speed2.isTouching(player)) {
    if (speedMultiplier != 1.5)    {
    effectCircle2.tint = "rgb(50, 255, 50)";
    effectCircle2.scale = 2; 
    }
    speedMultiplier = 1.5;
  }
  
  // 3x SPEED PORTALS
  if (speed3.isTouching(player)) {
    if (speedMultiplier != 1.75)    {
    effectCircle2.tint = "rgb(255, 50, 255)";
    effectCircle2.scale = 2; 
    }
    speedMultiplier = 1.75;
  }
}

function playerCheckpoints() {
  // Assigning value
  if (player.isTouching(checkpoint_1)) 
    currentCheckpoint = 1;
  if (player.isTouching(checkpoint_2)) 
    currentCheckpoint = 2;
  if (player.isTouching(checkpoint_3)) 
    currentCheckpoint = 3;
  if (player.isTouching(checkpoint_4)) 
    currentCheckpoint = 4;
  if (player.isTouching(checkpoint_5)) 
    currentCheckpoint = 5;
  
  
  // Changing sprite texture
  if (currentCheckpoint == 1) {
    checkpoint_1.setAnimation("checkpointActive");
  } else {
    checkpoint_1.setAnimation("checkpointInactive");
  }
  
  if (currentCheckpoint == 2) {
    checkpoint_2.setAnimation("checkpointActive");
  } else {
    checkpoint_2.setAnimation("checkpointInactive");
  }
  
  if (currentCheckpoint == 3) {
    checkpoint_3.setAnimation("checkpointActive");
  } else {
    checkpoint_3.setAnimation("checkpointInactive");
  }
  if (currentCheckpoint == 4) {
    checkpoint_4.setAnimation("checkpointActive");
  } else {
    checkpoint_4.setAnimation("checkpointInactive");
  }
  if (currentCheckpoint == 5) {
    checkpoint_5.setAnimation("checkpointActive");
  } else {
    checkpoint_5.setAnimation("checkpointInactive");
  }
}

function playerRespawn() {
  //Timer
  if (respawnTimer < 100) {
  respawnTimer = respawnTimer + 1;
  }
  
  // Halting movement
  if (respawnTimer < maxRespawn + 8) {
    player.velocityX = 0;
    player.velocityY = 0; 
  }
  ////////////////////////////////////////////////////////////////////////
  // Hazard detection
  if (spikes.isTouching(player) && respawnTimer > maxRespawn) {
     if (debugCollision == 0) {
      playSound("ExplodeSound.mp3");
      respawnCircle.tint = "rgb(255, 255, 0)";
      deathCount = deathCount + 1;
      respawnTimer = 0;
    }
  }
   if (player.y < -2200 && respawnTimer > maxRespawn) {
    if (debugCollision == 0) {
      playSound("ExplodeSound.mp3");
      respawnCircle.tint = "rgb(255, 255, 0)";
      respawnTimer = 0;
    }
  }
  ////////////////////////////////////////////////////////////////////////
  // Respawn events
  if (respawnTimer > 0 && respawnTimer < 5) {
    respawnCircle.scale = respawnCircle.scale + 1;
    camera.x = camera.x + randomNumber(-40,40);
    camera.y = camera.y + randomNumber(-40,40);
  }
  if (respawnTimer > 4 && respawnTimer < 30) {
    respawnCircle.scale = respawnCircle.scale - 0.3;
  }
  ////////////////////////////////////////////////////////////////////////
  // Respawning at a checkpoint
  if (respawnTimer == 30) {
    if (currentCheckpoint == 0) {
    player.x = 0;
    player.y = -200;
    currentSize = 0;
    currentGravity = 0;
    //speedMultiplier = 1.25;
    }
     if (currentCheckpoint == 1) {
    player.x = checkpoint_1.x;
    player.y = checkpoint_1.y + 40;
    currentSize = 0;
    currentGravity = 0;
    speedMultiplier = 1.25;
    }
     if (currentCheckpoint == 2) {
    player.x = checkpoint_2.x;
    player.y = checkpoint_2.y + 40;
    currentSize = 0;
    currentGravity = 0;
    speedMultiplier = 1.25;
    }
     if (currentCheckpoint == 3) {
    player.x = checkpoint_3.x;
    player.y = checkpoint_3.y + 40;
    currentSize = 0;
    currentGravity = 0;
    speedMultiplier = 1.25;
    }
     if (currentCheckpoint == 4) {
    player.x = checkpoint_4.x;
    player.y = checkpoint_4.y + 40;
    currentSize = 0;
    currentGravity = 0;
    speedMultiplier = 1.25;
    }
     if (currentCheckpoint == 5) {
    player.x = checkpoint_5.x;
    player.y = checkpoint_5.y + 40;
    currentSize = 0;
    currentGravity = 0;
    speedMultiplier = 1.25;
    }
  }
  ////////////////////////////////////////////////////////////////////////
  if (respawnTimer > 35 && respawnTimer < 70) {
    respawnCircle.tint = "rgb(0, 240, 255)";
    respawnCircle.scale = respawnCircle.scale + 0.1;
  }
  if (respawnTimer > 70 && respawnTimer < 76) {
    respawnCircle.scale = respawnCircle.scale - 1;
  }

  // Player visibility
  if (respawnTimer <= maxRespawn) {
    player.visible = 0;
  } else {
    player.visible = 1;
  }
  
  // Death sound
  if (respawnTimer == 35) {
    playSound("DeathSound.mp3");
  }
  
  // Circle following the player
  respawnCircle.x = player.x;
  respawnCircle.y = player.y + 25;
  
  // Scale reset
  if (respawnCircle.scale < 0.00) {
    respawnCircle.scale = 0;
  }
}

function BG() {
// MAKING THE BACKGROUND DO ITS THING
backgroundObj.x = camera.x / 1.4;
backgroundObj2.x = (camera.x / 1.4) + 1600;

if (player.x < checkpoint_1.x) {
  backgroundObj.tint = "rgb(100, 150, 235)";
  backgroundObj2.tint = "rgb(100, 150, 235)";
  groundObj.tint = "rgb(60, 120, 215)";
  groundObj2.tint = "rgb(60, 120, 215)";
  }
if (player.x >= checkpoint_1.x && player.x < checkpoint_2.x) {
  backgroundObj.tint = "rgb(255, 100, 240)";
  backgroundObj2.tint = "rgb(255, 100, 240)";
  groundObj.tint = "rgb(240, 100, 240)";
  groundObj2.tint = "rgb(240, 100, 240)";
  }
if (player.x >= checkpoint_2.x && player.x < checkpoint_3.x) {
  backgroundObj.tint = "rgb(150, 225, 75)";
  backgroundObj2.tint = "rgb(150, 225, 75)";
  groundObj.tint = "rgb(130, 205, 75)";
  groundObj2.tint = "rgb(130, 205, 75)";
  }
if (player.x >= checkpoint_3.x && player.x < checkpoint_4.x) {
  backgroundObj.tint = "rgb(225, 90, 90)";
  backgroundObj2.tint = "rgb(225, 90, 90)";
  groundObj.tint = "rgb(215, 80, 80)";
  groundObj2.tint = "rgb(215, 80, 80)";
  }
if (player.x >= checkpoint_4.x && player.x < checkpoint_5.x) {
  backgroundObj.tint = "rgb(170, 70, 230)";
  backgroundObj2.tint = "rgb(170, 70, 230)";
  groundObj.tint = "rgb(160, 60, 220)";
  groundObj2.tint = "rgb(160, 60, 220)";
  }
if (player.x >= checkpoint_5.x) {
  backgroundObj.tint = "rgb(100, 100, 100)";
  backgroundObj2.tint = "rgb(100, 100, 100)";
  groundObj.tint = "rgb(90, 90, 90)";
  groundObj2.tint = "rgb(90, 90, 90)";
  }
}

function cameraMovement() {
  // Camera Y lock
if (player.y < -450) {
  camera.y = player.y;
} else {
  camera.y = -449;
  }
  
if (player.y < -1200) {
  camera.y = -1199;
} 

  
  // Camera X lock
  if (player.x > 25) {
  camera.x = player.x;
} else {
  camera.x = 26;
  }
   if (player.x > 42600) {
     camera.x = 42600;
  }
}

function infiniteGround() {
  // GROUND
  if (player.x > (groundObj.x + 1200)) {
    groundObj.x = groundObj.x + 2400;
  }
  if (player.x > (groundObj2.x + 1200)) {
    groundObj2.x = groundObj2.x + 2400;
  }
  if (player.x < (groundObj.x - 1200)) {
    groundObj.x = groundObj.x - 2400;
  }
  if (player.x < (groundObj2.x - 1200)) {
    groundObj2.x = groundObj2.x - 2400;
  }
}

function effectCircleFunc() {
  // Circle 1
  effectCircle.x = player.x;
  effectCircle.y = player.y;
  effectCircle.scale = effectCircle.scale - 0.25;
  
  if (effectCircle.scale < 0) {
    effectCircle.scale = 0;
  }
  
  // Circle 2
  effectCircle2.x = player.x;
  effectCircle2.y = player.y;
  effectCircle2.scale = effectCircle2.scale - 0.25;
  
  if (effectCircle2.scale < 0) {
    effectCircle2.scale = 0;
  }
  
}

function playerBounds() {
  if (player.x < -425) {
    player.x = -424;
  }
  if (player.y > -150) {
    player.y = -200;
  }
}

function playerGravity() {
if (!player.collide(block)) {
  if (currentGravity == 0) {
  player.velocityY = player.velocityY + 5;
  } else {
  player.velocityY = player.velocityY - 5;
  }
}
  if (player.velocityY > 75) {
    player.velocityY = 75;
  }
  if (player.velocityY < -75) {
    player.velocityY = -75;
  }
}

function infiniteBackground() {
  // BACKGROUND (code must be duplicated for some reason)
  // SET 1
  if (player.x > (backgroundObj.x + 1600)) {
    backgroundObj.x = backgroundObj.x + 3200;
  }
  if (player.x > (backgroundObj2.x + 1600)) {
    backgroundObj2.x = backgroundObj2.x + 3200;
  }
  if (player.x < (backgroundObj.x - 1600)) {
    backgroundObj.x = backgroundObj.x - 3200;
  }
  if (player.x < (backgroundObj2.x - 1600)) {
    backgroundObj2.x = backgroundObj2.x - 3200;
  }
  
  // SET 2
  if (player.x > (backgroundObj.x + 1600)) {
    backgroundObj.x = backgroundObj.x + 3200;
  }
  if (player.x > (backgroundObj2.x + 1600)) {
    backgroundObj2.x = backgroundObj2.x + 3200;
  }
  if (player.x < (backgroundObj.x - 1600)) {
    backgroundObj.x = backgroundObj.x - 3200;
  }
  if (player.x < (backgroundObj2.x - 1600)) {
    backgroundObj2.x = backgroundObj2.x - 3200;
  }
  
  //SET 3
  if (player.x > (backgroundObj.x + 1600)) {
    backgroundObj.x = backgroundObj.x + 3200;
  }
  if (player.x > (backgroundObj2.x + 1600)) {
    backgroundObj2.x = backgroundObj2.x + 3200;
  }
  if (player.x < (backgroundObj.x - 1600)) {
    backgroundObj.x = backgroundObj.x - 3200;
  }
  if (player.x < (backgroundObj2.x - 1600)) {
    backgroundObj2.x = backgroundObj2.x - 3200;
  }
  
   //SET 4
  if (player.x > (backgroundObj.x + 1600)) {
    backgroundObj.x = backgroundObj.x + 3200;
  }
  if (player.x > (backgroundObj2.x + 1600)) {
    backgroundObj2.x = backgroundObj2.x + 3200;
  }
  if (player.x < (backgroundObj.x - 1600)) {
    backgroundObj.x = backgroundObj.x - 3200;
  }
  if (player.x < (backgroundObj2.x - 1600)) {
    backgroundObj2.x = backgroundObj2.x - 3200;
  }
  
   //SET 5
  if (player.x > (backgroundObj.x + 1600)) {
    backgroundObj.x = backgroundObj.x + 3200;
  }
  if (player.x > (backgroundObj2.x + 1600)) {
    backgroundObj2.x = backgroundObj2.x + 3200;
  }
  if (player.x < (backgroundObj.x - 1600)) {
    backgroundObj.x = backgroundObj.x - 3200;
  }
  if (player.x < (backgroundObj2.x - 1600)) {
    backgroundObj2.x = backgroundObj2.x - 3200;
  }
}
