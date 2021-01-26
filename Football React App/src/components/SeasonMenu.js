import React, { useState, useEffect } from 'react';
import axios from "axios";
import Box from '@material-ui/core/Box';
import MenuItem from '@material-ui/core/MenuItem';
import Menu from '@material-ui/core/Menu';
import EventIcon from '@material-ui/icons/Event';
import IconButton from '@material-ui/core/IconButton';

function SeasonMenu(props) {
    
    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);
    const handleMenu = event => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = (e, row) => {
        setAnchorEl(null);
        alert(props.seasonID);
    };

    const [data, setData] = useState([]);

    useEffect(() => {
      const fetchData = async () => {
        const response = await axios.get('http://localhost:8090/api/season/last');
        setData(response.data);
      };
      fetchData();
    }, []);

    return (
        <Box>
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
                {data.map((row, i) => (
                <MenuItem onClick={e => handleClose(e, row)} key={i}>{row.competition.title} {row.name}</MenuItem>
                ))}
            </Menu>
        </Box>
    );
}

export default SeasonMenu;