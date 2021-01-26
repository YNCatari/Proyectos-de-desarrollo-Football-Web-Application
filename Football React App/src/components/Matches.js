import React, { useState, useEffect } from 'react';
import axios from "axios";
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import List from '@material-ui/core/List';
import Box from '@material-ui/core/Box';
import ListItem from '@material-ui/core/ListItem';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';
import Typography from '@material-ui/core/Typography';
import { useHistory } from 'react-router-dom';

import ContentLoader from "react-content-loader";

const MyLoader = () => (
  <ContentLoader
      height={85}
      width={400}
      speed={2}
      primaryColor="#f3f3f3"
      secondaryColor="#ecebeb"
    >
      <rect x="18" y="9" rx="0" ry="0" width="32" height="31" /> 
      <rect x="65" y="19" rx="0" ry="0" width="202" height="13" /> 
      <rect x="302" y="18" rx="0" ry="0" width="83" height="51" /> 
      <rect x="18" y="48" rx="0" ry="0" width="32" height="31" /> 
      <rect x="66" y="56" rx="0" ry="0" width="202" height="13" />
    </ContentLoader>
);

const BoxLoading = () => (
  <Box>
    <Grid container spacing={0} direction="row" alignItems="stretch" className="box-matches">
      {[...Array(20)].map((x,i) =>
      <Grid item xs={12} md={6} key={i}>
        <Paper className="match-item"><MyLoader /></Paper>
      </Grid>
      )}
    </Grid>
  </Box>
);

function Matches(props)
{
    let history = useHistory();
    const handleMath = (e, row) =>
    {
      history.push(`/match/${row.id}`);
    }

    const idseason = props.seasonID;

    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
      const fetchData = async () => {
        const response = await axios.get(`http://localhost:8090/api/season/${idseason}/matches`);
        setData(response.data);
        setLoading(false);
      };
      fetchData();
    }, []);

    const BoxMatches = () => (
        <Box>
        {data.rounds.map((row, i) => (
        <Box key={i}>
        <Paper className="match-round-box">
          <Typography>{row.name}</Typography>
        </Paper>
        <Grid container spacing={0} direction="row" alignItems="stretch" className="box-matches">
          {row.matches.map((row, i) => (
          <Grid item xs={12} md={6} key={i} onClick={e => handleMath(e, row)}>
            <Paper className="match-item">
              <Grid container direction="row" alignItems="center" justify="space-between">
                <Grid item xs={8}>
                  <List dense>
                    <ListItem>
                      <ListItemAvatar className="team-image">
                        <Avatar variant="square" className="team-shield" src={row.teamLocal.logotype} alt={row.teamLocal.name} />
                      </ListItemAvatar>
                      <ListItemText primary={row.teamLocal.name}/>
                      <ListItemSecondaryAction className="goal-score">{row.state===0 ? null : row.goalLocal}</ListItemSecondaryAction>
                    </ListItem>
                    <ListItem>
                      <ListItemAvatar className="team-image">
                      <Avatar variant="square" className="team-shield" src={row.teamVisitor.logotype} alt={row.teamVisitor.name} />
                      </ListItemAvatar>
                      <ListItemText primary={row.teamVisitor.name}/>
                      <ListItemSecondaryAction className="goal-score">{row.state===0 ? null : row.goalVisitor}</ListItemSecondaryAction>
                    </ListItem>
                  </List>
                </Grid>
                <Grid item xs={4} className="border-left">
                  <Box className="match-date-item">
                    {row.state===0 ?
                    <Box>
                    <Box className="match-date">{row.date}</Box>
                    <Box className="match-hour">{row.hour}</Box>
                    </Box>
                    :
                    <Box>
                    <Box className="match-date">Fin</Box>
                    <Box className="match-hour">{row.date}</Box>
                    </Box>
                    }
                  </Box>
                </Grid>
              </Grid>
            </Paper>
          </Grid>
          ))}
        </Grid>
        </Box>
        ))}
        </Box>
    );

    return(
      <Box>
        {loading ? BoxLoading : BoxMatches}
      </Box>
    );
}

export default Matches;