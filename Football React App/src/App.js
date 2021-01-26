import React, { useState, useEffect, Suspense } from 'react';
import axios from "axios";

import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Box from '@material-ui/core/Box';
import CircularProgress from '@material-ui/core/CircularProgress';

import { makeStyles } from '@material-ui/core/styles';

import MenuItem from '@material-ui/core/MenuItem';
import Menu from '@material-ui/core/Menu';
import EventIcon from '@material-ui/icons/Event';
import IconButton from '@material-ui/core/IconButton';

import Matches from './components/Matches';
import Positions from './components/Positions';
import Statistics from './components/Statistics';
import { Goals as goals, Assists as assists } from './components/RowsData'; 

const TabPanel = React.lazy(() => import('./components/TabPanel'));

const useStyles = makeStyles(theme => ({
  root: {
    backgroundColor: '#F2F2F2'
  },
  AppBarMain: {
    flexGrow: 1
  },
  AppBarTab: {
    boxShadow: 'none'
  },
  indicator: {
    backgroundColor: '#FFFFFF'
  },
  title: {
    flexGrow: 1
  },
}));

function a11yProps(index) {
  return {
    id: `scrollable-auto-tab-${index}`,
    'aria-controls': `scrollable-auto-tabpanel-${index}`,
  };
}

const BoxLoading = () => (
  <Box p={5} display="flex" justifyContent="center"><CircularProgress disableShrink /></Box>
);

function App() {

  const classes = useStyles();
  const [tab, setTab] = useState(0);
  const handleChangeTab = (event, newValue) => {
    setTab(newValue);
  };

  
  //States
  const [seasonID, setSeasonID] = useState(0);
  const [loading, setLoading] = useState(true);

  const [dataMenu, setDataMenu] = useState([]);


    useEffect(() => {
      
      const fetchData = async () => {
        const response = await axios.get('http://localhost:8090/api/season/of-last');
          if(seasonID !== response.data.id) setSeasonID(response.data.id);
          setLoading(false);
      };

      const fetchDataMenu = async () => {
        const response = await axios.get('http://localhost:8090/api/season/last');
        setDataMenu(response.data);
      };

      fetchData();
      fetchDataMenu();

    }, []);


    //Menu
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const handleMenu = event => {
      setAnchorEl(event.currentTarget);
  };
  const handleClose = (e, row) => {
      setAnchorEl(null);
      //alert(row.id);
      setSeasonID(row.id);
  };


  const BoxMain = () => (
    <Box className={classes.root}>
      <AppBar position="sticky" className={classes.AppBarMain}>
        <Toolbar variant="dense">
          <Typography variant="h6" className={classes.title}>
            Liga de Fútbol Pilcuyo
          </Typography>
          <IconButton onClick={handleMenu} color="inherit">
                <EventIcon />
            </IconButton>
            <Menu
                id="menu-appbar"
                anchorEl={anchorEl}
                keepMounted
                anchorOrigin={{vertical: 'top', horizontal: 'right',}}
                transformOrigin={{vertical: 'top', horizontal: 'right',}}
                open={open}
                onClose={handleClose}
            >
                {dataMenu.map((row, i) => (
                <MenuItem onClick={e => handleClose(e, row)} key={i}>{row.competition.title} {row.name}</MenuItem>
                ))}
            </Menu>
        </Toolbar>
        <AppBar position="relative" className={classes.AppBarTab}>
          <Tabs
            value={tab}
            onChange={handleChangeTab}
            classes={{indicator: classes.indicator}}
            variant="scrollable"
            scrollButtons="off"
            className="justify-content-center"
          >
            <Tab label="Partidos" {...a11yProps(0)} />
            <Tab label="Posiciones" {...a11yProps(1)} />
            <Tab label="Estadísticas" {...a11yProps(2)} />
          </Tabs>
        </AppBar>
      </AppBar>
      <Container maxWidth="md">
        <Suspense fallback={<Box p={5} display="flex" justifyContent="center"><CircularProgress disableShrink /></Box>}>
          <TabPanel value={tab} index={0}>
            <Matches seasonID={seasonID} />
          </TabPanel>
          <TabPanel value={tab} index={1}>
            <Positions seasonID={seasonID} />
          </TabPanel>
          <TabPanel value={tab} index={2}>
            <Statistics filas={goals} asistencias={assists} />
          </TabPanel>
        </Suspense>
      </Container>
    </Box>
  );

  return (
    <Box>{loading ? BoxLoading : BoxMain}</Box>
  );
}

export default App;
