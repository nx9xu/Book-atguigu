package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {

    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品项
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        // 先查看购物车中是否已添加过此商品
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            // 之前没添加过该商品
            items.put(cartItem.getId(), cartItem);
        } else {
            // 之前添加过该商品
            item.setCount(item.getCount() + 1); // 数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount()))); // 更新总金额
        }
    }

    /**
     * 删除商品项
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改商品数量
     *
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        // 先查看购物车中是否有此商品
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(count); // 更新商品数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount()))); // 更新总金额
        }
    }


    public Integer getTotalCount() {
        Integer totalCount = 0;

        // 下面两种方法任选其一
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
//        for (CartItem value : items.values()) {
//            totalCount += value.getCount();
//        }

        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (CartItem value : items.values()) {
            totalPrice = totalPrice.add(value.getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
