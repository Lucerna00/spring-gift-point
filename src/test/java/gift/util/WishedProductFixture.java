package gift.util;

import gift.member.entity.Member;
import gift.product.entity.Product;
import gift.wishedproduct.entity.WishedProduct;

public class WishedProductFixture {

    public static WishedProduct createWishedProduct(Member member, Product product) {
        return new WishedProduct(member, product);
    }
}
