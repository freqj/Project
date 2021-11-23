package service.impl;

import dao.database.exeptions.DAOExeption;
import dao.database.impl.DAOFactory;
import domain.entity.Lot;
import service.LotsService;
import service.exeption.ServiceExeption;
import service.validator.ServiceValidator;


import java.util.List;
import java.util.Map;

import static domain.entity.UserFields.*;

public class LotServiceImpl implements LotsService {

    @Override
    public List<Lot> getAllLots() throws ServiceExeption {
        List<Lot> result;
        DAOFactory daoFactory = new DAOFactory();
        List<Lot> lots = null;
        try {
            return daoFactory.getLotsDAO().getAll(1, 1);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to get all lots from database", e);
        }
    }

    @Override
    public List<Lot> getAllActiveLots(int offset, int recordsPerPage) throws ServiceExeption {
        List<Lot> result;
        DAOFactory daoFactory = new DAOFactory();
        List<Lot> lots = null;
        try {
            return daoFactory.getLotsDAO().getActiveLots(offset, recordsPerPage);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to get all active lots", e);
        }
    }

    @Override
    public List<Lot> getTagActiveLots(int offset, int recordsPerPage, String tag) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getLotsDAO().getLotsByTag(offset, recordsPerPage,tag);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to get all active lots", e);
        }
    }

    @Override
    public int getNumberOfTagPages(int recordPerPage, String tag) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try
        {
            int result = daoFactory.getLotsDAO().getNumberOfActiveLotsByTag(tag);
            return (int) Math.ceil(result/(double) recordPerPage);
        } catch (DAOExeption daoExeption) {
            throw new ServiceExeption("Failed to get number of records",daoExeption);
        }
    }

    @Override
    public int getNumberOfPages(int recordPerPage) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try
        {
            int result = daoFactory.getLotsDAO().getNumberOfActiveLots();
            return (int) Math.ceil(result/(double) recordPerPage);
        } catch (DAOExeption daoExeption) {
            throw new ServiceExeption("Failed to get number of records",daoExeption);
        }
    }

    @Override
    public Lot getLotByID(int id) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try{
            return daoFactory.getLotsDAO().getLotByID(id);
        } catch (DAOExeption daoExeption) {
            throw new ServiceExeption("Failed to select lot", daoExeption);
        }
    }

    @Override
    public boolean deleteLotByID(int id) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getLotsDAO().deleteLotById(id);
        } catch (DAOExeption daoExeption) {
            throw new ServiceExeption("Failed to delete lot!", daoExeption);
        }
    }

    @Override
    public boolean saveLot(Map<String, String> lotMap) throws ServiceExeption {
        Lot lot = new Lot.LotBuilder()
                .setPrice(Integer.parseInt(lotMap.get(PRICE)))
                .setTitle(lotMap.get(TITLE))
                .setUserOwnerID(Integer.parseInt(lotMap.get(OWNER_ID)))
                .setDescription(lotMap.get(DESCRIPTION))
                .setStatus("active")
                .setTagList(lotMap.get(TAG_LIST))
                .setImage(lotMap.get(IMAGE))
                .create();

        if (!ServiceValidator.validate(lot)) {
            throw new ServiceExeption("Lot validation failed");
        }
        DAOFactory daoFactory = new DAOFactory();

        try {
            return daoFactory.getLotsDAO().saveLot(lot);
        } catch (DAOExeption daoExeption) {
            throw new ServiceExeption("Failed to save lot!", daoExeption);
        }
    }

    @Override
    public boolean changeLotPriceByID(int id, int price) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getLotsDAO().changeLotPriceById(id, price);

        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to change lot price", e);
        }
    }

    @Override
    public boolean changeLotStatusByID(int id, String status) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try {
            return daoFactory.getLotsDAO().changeLotStatusById(id, status);

        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to change lot status" ,e);
        }
    }

    @Override
    public boolean changeLotTitleByID(int id, String title) throws ServiceExeption {
        DAOFactory daoFactory = new DAOFactory();
        try {
            daoFactory.getLotsDAO().changeLotTitleById(id,title);
        } catch (DAOExeption e) {
            throw new ServiceExeption("Failed to change lot title",e);
        }

        return false;
    }


}
