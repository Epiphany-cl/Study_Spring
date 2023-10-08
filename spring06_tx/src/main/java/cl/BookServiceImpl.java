package cl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Transactional //开启事务
    @Override
    public void buyBook(Integer bookId, Integer userId) throws FileNotFoundException {
        //查询图书的价格
        Integer price = bookDao.getPriceByBookId(bookId);
        //更新图书的库存
        bookDao.updateStock(bookId);

        //默认编译时异常不会回滚
        FileOutputStream fileOutputStream = new FileOutputStream("x:/a.txt");

        //更新用户的余额
        bookDao.updateBalance(userId, price);
    }
}