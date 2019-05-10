
%   Blocks World

%  To run this example, first consult the planner you want to use
%  (strips.pl or idstrips.pl) and then consult the blocks.pl example
%  In the query window, run the goal:
%  ?- plan.


% Action_Move_From_Room_To_Room
act( move(FromRoom, ToRoom),                             % action name
     [block(X), handempty,  clear(X), on(X,table)],      % preconditions
     [handempty, on(X, table)],                          % delete
     [holding(X)]                                        % add
     ).

% Action_Climb_Boxes
act( climbUp(Box),
     [block(X), holding(X)],
     [holding(X)],
     [handempty, on(X,table)]
     ).

act( climbDown(Box),
     [block(X), holding(X), block(Y), clear(Y), diff(X,Y)],
     [holding(X), clear(Y)],
     [handempty, on(X,Y)]
     ).

% Action_Move_Box_From_Room_To_Room
act( pushBox(Box, FromRoom, ToRoom),
     [block(X), handempty, clear(X), on(X,Y), block(Y), diff(X,Y)],  % (1)
     [handempty, on(X, Y)],
     [holding(X), clear(Y)]
     ).

% Action_Light_Switches
act( switchOn(LightSwitch),
     [block(X), holding(X), block(Y), clear(Y), diff(X,Y)],
     [holding(X), clear(Y)],
     [handempty, on(X,Y)]
     ).

act( switchOff(LightSwitch),
     [block(X), holding(X), block(Y), clear(Y), diff(X,Y)],
     [holding(X), clear(Y)],
     [handempty, on(X,Y)]
     ).

% Find_Plans_For_Goals
goal_state(
    [
        in(shakey, room3),
        switch(light1, false),
        in(box2, room2)
    ]).

initial_state(
    [
     % Shakey
        shakey(shakey),
        in(shakey, room3),
        on(shakey, floor),

     % Rooms
        room(room1),
        room(room2),
        room(room3),
        room(room4),

     % Boxes
        box(box1),
        box(box2),
        box(box3),
        box(box4),
     % Boxes_Location
        in(box1, room1),
        in(box2, room1),
        in(box3, room1),
        in(box4, room1),

     % Light_Switches
        switch(light1, true),
        switch(light2, false),
        switch(light3, false),
        switch(light4, true),
     % Light_Switches_Location
        in(box1, room1),
        in(box2, room1),
        in(box3, room1),
        in(box4, room1),

     % Connections
        connect(room1, corridor),
        connect(room2, corridor),
        connect(room3, corridor),
        connect(room4, corridor),

        connect(corridor, room1),
        connect(corridor, room2),
        connect(corridor, room3),
        connect(corridor, room4),

        connect(room1, light1),
        connect(room2, light2),
        connect(room3, light3),
        connect(room4, light4),

        connect(light1, room1),
        connect(light2, room2),
        connect(light3, room3),
        connect(light4, room4)

    ]).