import React, { useState, useEffect } from 'react';
import axios from "axios";
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import Container from '@material-ui/core/Container';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box'

import { useHistory, useParams } from 'react-router-dom';

//Tab
import PropTypes from 'prop-types';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';

//Picture
import Avatar from '@material-ui/core/Avatar';

//Table
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

import CircularProgress from '@material-ui/core/CircularProgress';


function TabPanel(props)
{
  const { children, value, index, ...other } = props;
  return(
    <Box
      component="div"
      role="tabpanel"
      hidden={value !== index}
      id={`full-width-tabpanel-${index}`}
      aria-labelledby={`full-width-tab-${index}`}
      {...other}
    >
      <Box>{children}</Box>
    </Box>
  );
}


TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired
};

function a11yPros(index)
{
  return {
    id: `full-width-tab-${index}`,
    'aria-controls' : `full-width-tabpanel-${index}`
  };
}


const useStyles = makeStyles(theme => ({
    root: {
      flexGrow: 1,
    },
    menuButton: {
      marginRight: theme.spacing(2),
    },
    title: {
      flexGrow: 1,
      fontWeight: 400,
      fontSize: 18
    },
    AppBar:{
        background: '#212121'
    },
    Paper: {
        borderRadius: 0
    },
    BoxScore: {
      padding: theme.spacing(2, 3)
    },
    AppBarTab: {
      background: '#FFFFFF',
      color: '#444444'
    },
    TabIndicator: {
      backgroundColor: '#3F51B5'
    },
    PaperStadium: {
      backgroundColor: '#E8E9EA',
      padding: theme.spacing(2, 2),
      borderRadius: 0
    }
  }));


  const BoxLoading = () => (
    <Box p={5} display="flex" justifyContent="center"><CircularProgress disableShrink /></Box>
  );


function Match() {
    const classes = useStyles();
    
    let history = useHistory();
    function handleBack() {
      history.push('/');
      //history.goBack();
    }

    //Params
    let { id } = useParams();

    //Tab
    const [tab, setTab] = useState(1);
    const handleChange = (event, newValue) => {
      setTab(newValue);
    }

    //states
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    //useEffect
    useEffect(() => {
      const fetchData = async () => {
        const response = await axios.get(`http://localhost:8090/api/match/${id}`);
        setData(response.data);
        console.log(response.data);
        setLoading(false);
      };
      fetchData();
    }, []);

    const BoxMatchDetail = () => (
      <Box className={classes.root}>
      <AppBar position="sticky" className={classes.AppBar}>
        <Toolbar>
          <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu" onClick={handleBack}>
            <ArrowBackIcon />
          </IconButton>
          <Typography variant="h6" className={classes.title}>
          {data.teamLocal.name} vs {data.teamVisitor.name}
          </Typography>
        </Toolbar>
      </AppBar>
      <Container maxWidth="md">
          <Paper className={classes.Paper}>
              <Box className={classes.BoxScore}>
                <Box display="flex" mb={3} className="box-header-i">
                  {data.state === 0 ?
                  <Box flexGrow={1}>{data.date} - {data.hour}</Box>
                  :
                  <Box flexGrow={1}>{data.date}</Box>
                  }
                  {data.state === 1 &&
                  <Box>Finalizado</Box>
                  }
                </Box>
                <Box display="flex" alignItems="center">
                  <Box align="center" className="match-team-box">
                    <Avatar variant="square" className="match-team-shield" src={data.teamLocal.logotype} alt={data.teamLocal.name} />
                    <Typography className="match-team-name">{data.teamLocal.name}</Typography>
                  </Box>
                  <Box display="flex" alignItems="center" flexGrow={2}>
                    <Box flexGrow={1} align="center" className="match-score-local">{data.goalLocal}</Box>
                    <Box flexGrow={1} align="center" className="ms-separator">-</Box>
                    <Box flexGrow={1} align="center" className="match-score-visitor">{data.goalVisitor}</Box>
                  </Box>
                  <Box align="center" className="match-team-box">
                    <Avatar variant="square" className="match-team-shield" src={data.teamVisitor.logotype} alt={data.teamVisitor.name} />
                    <Typography className="match-team-name">{data.teamVisitor.name}</Typography>
                  </Box>
                </Box>
              </Box>

              
              <AppBar position="static" className={classes.AppBarTab}>
                <Tabs
                  value={tab}
                  onChange={handleChange}
                  textColor="inherit"
                  variant="fullWidth"
                  classes={{indicator: classes.TabIndicator}}
                >
                  <Tab label="Alineaciones" {...a11yPros(0)}/>
                  <Tab label="Estadisticas" {...a11yPros(1)}/>
                </Tabs>
              </AppBar>
              
                <TabPanel value={tab} index={0}></TabPanel>
                <TabPanel value={tab} index={1}>
                  
                

  <Box p={2}>
    <Table className="tabla-estadisticas">
        <TableHead>
          <TableRow>
            <TableCell align="center"></TableCell>
            <TableCell align="center">Estadísticas del Partido</TableCell>
            <TableCell align="center"></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
    
            <TableRow>
              <TableCell align="center">7</TableCell>
              <TableCell align="center">Remates</TableCell>
              <TableCell align="center">4</TableCell>
            </TableRow>

            <TableRow>
              <TableCell align="center">1</TableCell>
              <TableCell align="center">Remates al arco</TableCell>
              <TableCell align="center">2</TableCell>
            </TableRow>

            <TableRow>
              <TableCell align="center">46%</TableCell>
              <TableCell align="center">Poseción</TableCell>
              <TableCell align="center">54%</TableCell>
            </TableRow>

            <TableRow>
              <TableCell align="center">395</TableCell>
              <TableCell align="center">Pases</TableCell>
              <TableCell align="center">480</TableCell>
            </TableRow>

            <TableRow>
              <TableCell align="center">77%</TableCell>
              <TableCell align="center">Precisión de los pases</TableCell>
              <TableCell align="center">82%</TableCell>
            </TableRow>

            <TableRow>
              <TableCell align="center">13</TableCell>
              <TableCell align="center">Faltas</TableCell>
              <TableCell align="center">25</TableCell>
            </TableRow>

            <TableRow>
              <TableCell align="center">3</TableCell>
              <TableCell align="center">Tarjetas amarillas</TableCell>
              <TableCell align="center">2</TableCell>
            </TableRow>

            <TableRow>
              <TableCell align="center">0</TableCell>
              <TableCell align="center">Tarjetas rojas</TableCell>
              <TableCell align="center">1</TableCell>
            </TableRow>

            <TableRow>
              <TableCell align="center">1</TableCell>
              <TableCell align="center">Posición adelantada</TableCell>
              <TableCell align="center">1</TableCell>
            </TableRow>

            <TableRow>
              <TableCell align="center">8</TableCell>
              <TableCell align="center">Tiros de esquina</TableCell>
              <TableCell align="center">4</TableCell>
            </TableRow>

        </TableBody>
      </Table>
      </Box>

                </TabPanel>
          

<Paper className={classes.PaperStadium}>
  <Typography>Estadio: {data.stadium}</Typography>
</Paper>


          </Paper>
      </Container>
    </Box>
    );


  return (
    <Box>
      {loading ? BoxLoading : BoxMatchDetail}
    </Box>
  );
}

export default Match;
