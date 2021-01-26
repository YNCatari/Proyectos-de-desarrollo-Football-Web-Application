-- STORED PROCEDURE FOOTBALL --

-- SP TEAM  [OK ALL]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_Team_Add
(
    fullname_ VARCHAR(200),
    nickname_ VARCHAR(100),
    initials_ VARCHAR(50),
    logotype_ VARCHAR(200),
	email_ VARCHAR(200),
	id_community_ INT
)
BEGIN
INSERT INTO tbl_team(fullname,nickname,initials,logotype,email,id_community) VALUES(fullname_,nickname_,initials_,logotype_,email_,id_community_);
COMMIT;
END//

-- ToUpdate [OK]
CREATE PROCEDURE SP_Team_ToUpdate
(
    id_ INT,
    fullname_ VARCHAR(200),
    nickname_ VARCHAR(100),
    initials_ VARCHAR(50),
    logotype_ VARCHAR(200),
	id_community_ INT
)
BEGIN
UPDATE tbl_team SET 
fullname = fullname_,
nickname = nickname_,
initials = initials_,
logotype = logotype_,
id_community = id_community_
WHERE id = id_;
COMMIT;
END//

-- Remove [OK]
CREATE PROCEDURE SP_Team_Remove(id_ INT)
BEGIN
DELETE FROM tbl_team WHERE id = id_;
COMMIT;
END//

-- ToList [OK]
CREATE PROCEDURE SP_Team_ToList()
BEGIN
SELECT t.id, t.fullname, t.nickname, t.initials, t.logotype, t.email, t.id_community, c.name 
FROM tbl_team t INNER JOIN tbl_community c ON t.id_community = c.id;
COMMIT;
END//

-- Select [OK]
CREATE PROCEDURE SP_Team_Select(id_ INT)
BEGIN
SELECT t.id, t.fullname, t.nickname, t.initials, t.logotype, t.email, t.id_community, c.name 
FROM tbl_team t INNER JOIN tbl_community c ON t.id_community = c.id WHERE t.id = id_;
COMMIT;
END//

DELIMITER ;


-- SP STADIUM [OK ALL]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_Stadium_Add
(
    name_ VARCHAR(200),
    address_ VARCHAR(200)
)
BEGIN
INSERT INTO tbl_stadium(name,address) VALUES(name_,address_);
COMMIT;
END//

-- ToUpdate [OK]
CREATE PROCEDURE SP_Stadium_ToUpdate
(
    id_ INT,
    name_ VARCHAR(200),
    address_ VARCHAR(200)
)
BEGIN
UPDATE tbl_stadium SET name = name_, address = address_ WHERE id = id_;
COMMIT;
END//

-- Remove [OK]
CREATE PROCEDURE SP_Stadium_Remove(id_ INT)
BEGIN
DELETE FROM tbl_stadium WHERE id = id_;
COMMIT;
END//

-- ToList [OK]
CREATE PROCEDURE SP_Stadium_ToList()
BEGIN
SELECT id, name, address FROM tbl_stadium;
COMMIT;
END//

-- Select [OK]
CREATE PROCEDURE SP_Stadium_Select(id_ INT)
BEGIN
SELECT id, name, address FROM tbl_stadium WHERE id = id_;
COMMIT;
END//

DELIMITER ;


-- SP COMPETITION [OK ALL]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_Competition_Add
(
    title_ VARCHAR(200)
)
BEGIN
INSERT INTO tbl_competition(title) VALUES(title_);
COMMIT;
END//

-- ToUpdate [OK]
CREATE PROCEDURE SP_Competition_ToUpdate
(
    id_ INT,
    title_ VARCHAR(100)
)
BEGIN
UPDATE tbl_competition SET title = title_ WHERE id = id_;
COMMIT;
END//

-- Remove [OK]
CREATE PROCEDURE SP_Competition_Remove(id_ INT)
BEGIN
DELETE FROM tbl_competition WHERE id = id_;
COMMIT;
END//

-- ToList [OK]
CREATE PROCEDURE SP_Competition_ToList()
BEGIN
SELECT id, title FROM tbl_competition;
COMMIT;
END//

-- Select [OK]
CREATE PROCEDURE SP_Competition_Select(id_ INT)
BEGIN
SELECT id, title FROM tbl_competition WHERE id = id_;
COMMIT;
END//

DELIMITER ;


-- SP COMMUNITY [OK ALL]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_Community_Add
(
    name_ VARCHAR(200)
)
BEGIN
INSERT INTO tbl_community(name) VALUES(name_);
COMMIT;
END//

-- ToUpdate [OK]
CREATE PROCEDURE SP_Community_ToUpdate
(
    id_ INT,
    name_ VARCHAR(100)
)
BEGIN
UPDATE tbl_community SET name = name_ WHERE id = id_;
COMMIT;
END//

-- Remove [OK]
CREATE PROCEDURE SP_Community_Remove(id_ INT)
BEGIN
DELETE FROM tbl_community WHERE id = id_;
COMMIT;
END//

-- ToList [OK]
CREATE PROCEDURE SP_Community_ToList()
BEGIN
SELECT id, name FROM tbl_community;
COMMIT;
END//

-- Select [OK]
CREATE PROCEDURE SP_Community_Select(id_ INT)
BEGIN
SELECT id, name FROM tbl_community WHERE id = id_;
COMMIT;
END//

DELIMITER ;



-- SP SEASON [OK ALL]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_Season_Add
(
    id_competition_ INT,
    name_ VARCHAR(100),
    start_date_ DATE,
    end_date_ DATE,
    state_ INT
)
BEGIN
INSERT INTO tbl_season(id_competition,name,start_date,end_date,state) VALUES(id_competition_,name_,start_date_,end_date_,state_);
COMMIT;
END//

-- ToUpdate [OK]
CREATE PROCEDURE SP_Season_ToUpdate
(
    id_ INT,
    id_competition_ INT,
    name_ VARCHAR(100),
    start_date_ DATE,
    end_date_ DATE
)
BEGIN
UPDATE tbl_season SET 
id_competition = id_competition_,
name = name_,
start_date = start_date_,
end_date = end_date_
WHERE id = id_;
COMMIT;
END//


-- ToUpdate State [OK]
CREATE PROCEDURE SP_Season_ToUpdateState
(
    id_ INT,
    state_ INT
)
BEGIN
UPDATE tbl_season SET state = state_ WHERE id = id_;
COMMIT;
END//

-- Remove [OK]
CREATE PROCEDURE SP_Season_Remove(id_ INT)
BEGIN
DELETE FROM tbl_season WHERE id = id_;
COMMIT;
END//

-- ToList [OK]
CREATE PROCEDURE SP_Season_ToList()
BEGIN
SELECT ss.id, ss.name, ss.id_competition, ct.title, ss.start_date, ss.end_date, ss.state 
FROM tbl_season ss INNER JOIN tbl_competition ct ON ss.id_competition = ct.id ORDER BY id DESC;
COMMIT;
END//

-- ToListMatch [OK] Listar Temporadas con Partidos
CREATE PROCEDURE SP_Season_ToListMatch()
BEGIN
SELECT ss.id, ss.name, ss.id_competition, ct.title, ss.start_date, ss.end_date, ss.state 
FROM tbl_season ss INNER JOIN tbl_competition ct ON ss.id_competition = ct.id WHERE ss.state <> 0 ORDER BY id DESC;
COMMIT;
END//

-- Select [OK]
CREATE PROCEDURE SP_Season_Select(id_ INT)
BEGIN
SELECT ss.id, ss.name, ss.id_competition, ct.title, ss.start_date, ss.end_date, ss.state 
FROM tbl_season ss INNER JOIN tbl_competition ct ON ss.id_competition = ct.id AND ss.id = id_;
COMMIT;
END//

-- Select Date [OK]
CREATE PROCEDURE SP_Season_SelectDate(id_ INT)
BEGIN
SELECT start_date FROM tbl_season WHERE id = id_;
COMMIT;
END//

DELIMITER ;


-- SP CLASSIFICATION [OK]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_Classification_Add
(
    id_season_ INT,
    id_team_ INT,
    matches_played_ INT,
    matches_win_ INT,
    matches_tied_ INT,
    matches_lost_ INT,
    goals_favor_ INT,
    goals_against_ INT,
    goals_difference_ INT,
    points_ INT
)
BEGIN
INSERT INTO tbl_classification(id_season,id_team,matches_played,matches_win,matches_tied,matches_lost,goals_favor,goals_against,goals_difference,points) 
VALUES(id_season_,id_team_,matches_played_,matches_win_,matches_tied_,matches_lost_,goals_favor_,goals_against_,goals_difference_,points_);
COMMIT;
END//

-- ToUpdate Points [OK]
CREATE PROCEDURE SP_Classification_ToUpdatePoints
(
    id_ INT,
    matches_played_ INT,
    matches_win_ INT,
    matches_tied_ INT,
    matches_lost_ INT,
    goals_favor_ INT,
    goals_against_ INT,
    goals_difference_ INT,
    points_ INT
)
BEGIN
UPDATE tbl_classification SET 
matches_played = matches_played_,
matches_win = matches_win_,
matches_tied = matches_tied_,
matches_lost = matches_lost_,
goals_favor = goals_favor_,
goals_against = goals_against_,
goals_difference = goals_difference_,
points = points_
WHERE id = id_;
COMMIT;
END//

-- ToListSeason [OK]
CREATE PROCEDURE SP_Classification_ToListSeason(season_ INT)
BEGIN
SELECT c.id, c.id_season, c.id_team, 
t.fullname, t.nickname, t.initials, t.logotype, 
c.matches_played, c.matches_win, c.matches_tied, c.matches_lost, c.goals_favor, 
c.goals_against, c.goals_difference, c.points
FROM tbl_classification c 
INNER JOIN tbl_season s ON c.id_season = s.id 
INNER JOIN tbl_team t ON c.id_team = t.id 
WHERE c.id_season = season_ ORDER BY c.points DESC, c.goals_difference DESC;
COMMIT;
END//

-- Select Team [OK]
CREATE PROCEDURE SP_Classification_SelectTeam(id_season_ INT, id_team_ INT)
BEGIN
SELECT id, id_season, id_team, matches_played, matches_win, matches_tied, matches_lost, goals_favor, goals_against, goals_difference, points FROM tbl_classification WHERE id_season = id_season_ AND id_team = id_team_;
COMMIT;
END//

DELIMITER ;


-- SP POSITION [OK ALL]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_Position_Add
(
    description_ VARCHAR(100)
)
BEGIN
INSERT INTO tbl_position(description) VALUES(description_);
COMMIT;
END//

-- ToUpdate [OK]
CREATE PROCEDURE SP_Position_ToUpdate
(
    id_ INT,
    description_ VARCHAR(100)
)
BEGIN
UPDATE tbl_position SET description = description_ WHERE id = id_;
COMMIT;
END//

-- Remove [OK]
CREATE PROCEDURE SP_Position_Remove(id_ INT)
BEGIN
DELETE FROM tbl_position WHERE id = id_;
COMMIT;
END//

-- ToList [OK]
CREATE PROCEDURE SP_Position_ToList()
BEGIN
SELECT id, description FROM tbl_position;
COMMIT;
END//

-- Select [OK]
CREATE PROCEDURE SP_Position_Select(id_ INT)
BEGIN
SELECT id, description FROM tbl_position WHERE id = id_;
COMMIT;
END//

DELIMITER ;


-- SP PLAYER [OK ALL]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_Player_Add
(
    dni_ VARCHAR(8),
    name_ VARCHAR(100),
    lastname_ VARCHAR(100),
    date_birth_ DATE,
    photo_ VARCHAR(200),
    phone_ VARCHAR(50),
    email_ VARCHAR(150),
    height_ DOUBLE,
    weight_ DOUBLE,
    foot_ VARCHAR(100),
    position_ INT,
    state_ INT
)
BEGIN
INSERT INTO tbl_player(dni,name,lastname,date_birth,photo,phone,email,height,weight,foot,position,state) 
VALUES(dni_,name_,lastname_,date_birth_,photo_,phone_,email_,height_,weight_,foot_,position_,state_);
COMMIT;
END//

-- ToUpdate [OK]
CREATE PROCEDURE SP_Player_ToUpdate
(
    id_ INT,
    dni_ VARCHAR(8),
    name_ VARCHAR(100),
    lastname_ VARCHAR(100),
    date_birth_ DATE,
    photo_ VARCHAR(200),
    phone_ VARCHAR(50),
    email_ VARCHAR(150),
    height_ DOUBLE,
    weight_ DOUBLE,
    foot_ VARCHAR(100),
    position_ INT,
    state_ INT
)
BEGIN
UPDATE tbl_player SET 
dni = dni_,
name = name_,
lastname = lastname_,
date_birth = date_birth_,
photo = photo_,
phone = phone_,
email = email_,
height = height_,
weight = weight_,
foot = foot_,
position = position_,
state = state_
WHERE id = id_;
COMMIT;
END//

-- Remove [OK]
CREATE PROCEDURE SP_Player_Remove(id_ INT)
BEGIN
DELETE FROM tbl_player WHERE id = id_;
COMMIT;
END//

-- ToList [OK]
CREATE PROCEDURE SP_Player_ToList()
BEGIN
SELECT py.id, py.dni, py.name, py.lastname, py.date_birth, py.photo, py.phone, py.email, py.height, py.weight, py.foot, py.position, ps.description, py.state 
FROM tbl_player py INNER JOIN tbl_position ps ON py.position = ps.id;
COMMIT;
END//

-- Select [OK]
CREATE PROCEDURE SP_Player_Select(id_ INT)
BEGIN
SELECT py.id, py.dni, py.name, py.lastname, py.date_birth, py.photo, py.phone, py.email, py.height, py.weight, py.foot, py.position, ps.description, py.state 
FROM tbl_player py INNER JOIN tbl_position ps ON py.position = ps.id WHERE py.id = id_;
COMMIT;
END//

DELIMITER ;


-- SP MATCH [OK]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_Match_Add
(
    id_season_ INT,
    id_stadium_ INT,
    id_team_local_ INT,
    id_team_visitor_ INT,
    match_date_ DATE,
    match_hour_ TIME,
    match_round_ INT,
    goal_local_ INT,
    goal_visitor_ INT,
    state_ INT
)
BEGIN
INSERT INTO tbl_match(id_season,id_stadium,id_team_local,id_team_visitor,match_date,match_hour,match_round,goal_local,goal_visitor,state) 
VALUES(id_season_,id_stadium_,id_team_local_,id_team_visitor_,match_date_,match_hour_,match_round_,goal_local_,goal_visitor_,state_);
COMMIT;
END//

-- ToUpdateGoals [OK]
CREATE PROCEDURE SP_Match_ToUpdateGoals
(
    id_ INT,
    goal_local_ INT,
    goal_visitor_ INT
)
BEGIN
UPDATE tbl_match SET goal_local = goal_local_, goal_visitor = goal_visitor_ WHERE id = id_;
COMMIT;
END//

-- ToListSeason [OK]
CREATE PROCEDURE SP_Match_ToListSeason(id_season_ INT)
BEGIN
SELECT 
mt.id, mt.id_season, mt.id_stadium, mt.id_team_local, mt.id_team_visitor, mt.match_date, mt.match_hour, mt.match_round, mt.goal_local, mt.goal_visitor, mt.state, 
tl.fullname AS 'tl_fullname', tl.nickname AS 'tl_nickname', tl.logotype AS 'tl_logotype', 
tv.fullname AS 'tv_fullname', tv.nickname AS 'tv_nickname', tv.logotype AS 'tv_logotype' 
FROM tbl_match mt 
INNER JOIN tbl_team tl ON mt.id_team_local = tl.id
INNER JOIN tbl_team tv ON mt.id_team_visitor = tv.id
WHERE mt.id_season = id_season_;
COMMIT;
END//

-- Select [OK]
CREATE PROCEDURE SP_Match_Select(id_ INT)
BEGIN
SELECT m.id, m.id_season, m.id_stadium, m.id_team_local, m.id_team_visitor, 
tl.fullname AS 'local_fullname', tl.nickname AS 'local_nickname', tl.initials AS 'local_initials', tl.logotype AS 'local_logotype', 
tv.fullname AS 'visitor_fullname', tv.nickname AS 'visitor_nickname', tv.initials AS 'visitor_initials', tv.logotype AS 'visitor_logotype', 
m.match_date, m.match_hour, m.match_round, m.goal_local, m.goal_visitor, m.state, 
st.name AS 'stadium_name', st.address AS 'stadium_address' 
FROM tbl_match m
INNER JOIN tbl_season s ON m.id_season = s.id 
INNER JOIN tbl_stadium st ON m.id_stadium = st.id
INNER JOIN tbl_team tl ON m.id_team_local = tl.id 
INNER JOIN tbl_team tv ON m.id_team_visitor = tv.id 
WHERE m.id = id_;
COMMIT;
END//

DELIMITER ;


-- SP REFEREE [OK ALL]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_Referee_Add
(
    name_ VARCHAR(100),
    lastname_ VARCHAR(100),
    phone_ VARCHAR(50),
    email_ VARCHAR(150)
)
BEGIN
INSERT INTO tbl_referee(name,lastname,phone,email) VALUES(name_,lastname_,phone_,email_);
COMMIT;
END//

-- ToUpdate [OK]
CREATE PROCEDURE SP_Referee_ToUpdate
(
    id_ INT,
    name_ VARCHAR(100),
    lastname_ VARCHAR(100),
    phone_ VARCHAR(50),
    email_ VARCHAR(150)
)
BEGIN
UPDATE tbl_referee SET name = name_, lastname = lastname_, phone = phone_, email = email_ WHERE id = id_;
COMMIT;
END//

-- Remove [OK]
CREATE PROCEDURE SP_Referee_Remove(id_ INT)
BEGIN
DELETE FROM tbl_referee WHERE id = id_;
COMMIT;
END//

-- ToList [OK]
CREATE PROCEDURE SP_Referee_ToList()
BEGIN
SELECT id, name, lastname, phone, email FROM tbl_referee;
COMMIT;
END//

-- Select [OK]
CREATE PROCEDURE SP_Referee_Select(id_ INT)
BEGIN
SELECT id, name, lastname, phone, email FROM tbl_referee WHERE id = id_;
COMMIT;
END//

DELIMITER ;


-- SP USER [OK]
DELIMITER //

-- Login [OK]
CREATE PROCEDURE SP_User_Login
(
    username_ VARCHAR(200),
    password_ VARCHAR(250)
)
BEGIN
SELECT id, username, state, role FROM tbl_user WHERE username = username_ AND password = password_;
COMMIT;
END//

DELIMITER ;


-- TEMPLATE

-- MATCH ALIGMENT


-- SP TYPE RESULT [OK ALL]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_TypeResult_Add
(
    description_ VARCHAR(100)
)
BEGIN
INSERT INTO tbl_typeresult(description) VALUES(description_);
COMMIT;
END//

-- ToUpdate [OK]
CREATE PROCEDURE SP_TypeResult_ToUpdate
(
    id_ INT,
    description_ VARCHAR(100)
)
BEGIN
UPDATE tbl_typeresult SET description = description_ WHERE id = id_;
COMMIT;
END//

-- Remove [OK]
CREATE PROCEDURE SP_TypeResult_Remove(id_ INT)
BEGIN
DELETE FROM tbl_typeresult WHERE id = id_;
COMMIT;
END//

-- ToList [OK]
CREATE PROCEDURE SP_TypeResult_ToList()
BEGIN
SELECT id, description FROM tbl_typeresult;
COMMIT;
END//

-- Select [OK]
CREATE PROCEDURE SP_TypeResult_Select(id_ INT)
BEGIN
SELECT id, description FROM tbl_typeresult WHERE id = id_;
COMMIT;
END//

DELIMITER ;


-- SP MATCH RESULT


-- SP REFEREE BODY


-- SP SEASON TEAM [OK]
DELIMITER //

-- Add [OK]
CREATE PROCEDURE SP_SeasonTeam_Add
(
    id_season_ INT,
    id_team_ INT
)
BEGIN
INSERT INTO tbl_seasonteam(id_season,id_team) VALUES(id_season_,id_team_);
COMMIT;
END//

-- ToListSeason [OK]
CREATE PROCEDURE SP_SeasonTeam_ToListSeason(season_ INT)
BEGIN
SELECT st.id, st.id_season, st.id_team, 
t.fullname, t.nickname, t.initials, t.logotype, t.email 
FROM tbl_seasonteam st 
INNER JOIN tbl_season s ON st.id_season = s.id
INNER JOIN tbl_team t ON st.id_team = t.id WHERE s.id = season_;
COMMIT;
END//

DELIMITER ;