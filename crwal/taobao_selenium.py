from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec
from selenium.webdriver.common.by import By
from selenium.common.exceptions import TimeoutException as te

wb=webdriver.Firefox()
wait=WebDriverWait(wb,10)


def search(shop=None):
    print(u"begain scarch")
    wb.get("https://www.taobao.com")
    try:
        element=wait.until(ec.presence_of_element_located((By.CSS_SELECTOR,'#q')))
        element.send_keys(u"{}".format(shop))
        submit=wait.until(ec.element_to_be_clickable((By.XPATH,'/html/body/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/form/div[1]/button')))
        submit.click()
    except te:
        return search()

def get_response():
    try:
        wait.until(ec.presence_of_all_elements_located((By.CSS_SELECTOR,"#mainsrp-itemlist  .items .items")))
        html=wb.page_source
        print(html)
        print("ff")
    except Exception:
        return
def next_page():
    next_element=wait.until(ec.presence_of_element_located((By.XPATH,"/html/body/div[1]/div[2]/div[3]/div[1]/div[26]/div/div/div/ul")))

    next_element.click()
if __name__ == '__main__':
        shop=input('input goods name')
        sum=0
        search(shop)
        while sum<10:
            get_response()
            next_page()

        wb.quit()

